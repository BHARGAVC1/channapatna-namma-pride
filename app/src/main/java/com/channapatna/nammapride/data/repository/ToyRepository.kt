package com.channapatna.nammapride.data.repository

import com.channapatna.nammapride.data.local.dao.ArtisanDao
import com.channapatna.nammapride.data.local.dao.ToyDao
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.ToyWithArtisan
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.data.remote.dto.toArtisan
import com.channapatna.nammapride.data.remote.dto.toToy
import com.channapatna.nammapride.util.NetworkHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

interface IToyRepository {
    fun getAllToys(): Flow<UiState<List<Toy>>>
    fun getAllArtisans(): Flow<UiState<List<Artisan>>>
    suspend fun verifyToy(id: String): UiState<ToyWithArtisan>
    suspend fun getArtisanById(id: String): UiState<Artisan>
    fun getToyByIdFlow(id: String): Flow<UiState<Toy>>
    suspend fun getToyById(id: String): UiState<Toy>
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
    fun getFavoriteToys(): Flow<UiState<List<Toy>>>
}

@Singleton
class ToyRepositoryImpl @Inject constructor(
    private val toyDao: ToyDao,
    private val artisanDao: ArtisanDao,
    private val firestore: FirebaseFirestore,   // replaces api
    private val auth: FirebaseAuth,
    private val networkHelper: NetworkHelper
) : IToyRepository {

    override fun getFavoriteToys(): Flow<UiState<List<Toy>>> = toyDao.getFavoriteToys()
        .map { toys ->
            if (toys.isEmpty()) UiState.Empty else UiState.Success(toys)
        }
        .onStart { emit(UiState.Loading) }
        .catch { e -> emit(UiState.Error("Failed to load favorites: ${e.localizedMessage}")) }

    override suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        val newState = !isFavorite
        toyDao.updateFavorite(id, newState)   // instant local update

        val uid = auth.currentUser?.uid ?: return
        val ref = firestore
            .collection("users").document(uid)
            .collection("favorites").document(id)
        if (newState) ref.set(mapOf("addedAt" to com.google.firebase.firestore.FieldValue.serverTimestamp()))
        else ref.delete()
        // fire-and-forget — Firestore queues offline writes automatically
    }

    override fun getAllToys(): Flow<UiState<List<Toy>>> = callbackFlow {
        var dataSent = false
        trySend(UiState.Loading)
        
        // 1. Immediate local cache check
        val initialCached = toyDao.getAllToys().first()
        if (initialCached.isNotEmpty()) {
            trySend(UiState.Success(initialCached))
            dataSent = true
        }

        // 2. Listen for Firestore updates
        val reg = firestore.collection("toys")
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    // If offline and we already have data, don't show an error screen
                    if (!dataSent) {
                        launch(Dispatchers.IO) {
                            val currentCache = toyDao.getAllToys().first()
                            if (currentCache.isEmpty()) {
                                trySend(UiState.Error("Could not load data. Check your connection."))
                            } else {
                                trySend(UiState.Success(currentCache))
                            }
                        }
                    }
                    return@addSnapshotListener
                }
                
                val remote = snap?.documents?.mapNotNull { it.toToy() } ?: emptyList()
                
                launch(Dispatchers.IO) {
                    if (remote.isNotEmpty()) {
                        val favIds = toyDao.getFavoriteIds().toSet()
                        val updatedRemote = remote.map {
                            it.copy(isFavorite = it.toyId in favIds)
                        }
                        toyDao.insertAll(updatedRemote)
                        trySend(UiState.Success(updatedRemote))
                        dataSent = true
                    } else if (!dataSent) {
                        val currentCache = toyDao.getAllToys().first()
                        if (currentCache.isEmpty()) trySend(UiState.Empty)
                        else {
                            trySend(UiState.Success(currentCache))
                            dataSent = true
                        }
                    }
                }
            }
        awaitClose { reg.remove() }
    }

    override fun getAllArtisans(): Flow<UiState<List<Artisan>>> = callbackFlow {
        var dataSent = false
        trySend(UiState.Loading)
        
        val initialCached = artisanDao.getAllArtisans().first()
        if (initialCached.isNotEmpty()) {
            trySend(UiState.Success(initialCached))
            dataSent = true
        }

        val reg = firestore.collection("artisans")
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    if (!dataSent) {
                        launch(Dispatchers.IO) {
                            val currentCache = artisanDao.getAllArtisans().first()
                            if (currentCache.isEmpty()) {
                                trySend(UiState.Error("Could not load artisans. Check your connection."))
                            } else {
                                trySend(UiState.Success(currentCache))
                            }
                        }
                    }
                    return@addSnapshotListener
                }
                
                val remote = snap?.documents?.mapNotNull { it.toArtisan() } ?: emptyList()
                
                launch(Dispatchers.IO) {
                    if (remote.isNotEmpty()) {
                        artisanDao.insertAll(remote)
                        trySend(UiState.Success(remote))
                        dataSent = true
                    } else if (!dataSent) {
                        val currentCache = artisanDao.getAllArtisans().first()
                        if (currentCache.isEmpty()) trySend(UiState.Empty)
                        else {
                            trySend(UiState.Success(currentCache))
                            dataSent = true
                        }
                    }
                }
            }
        awaitClose { reg.remove() }
    }

    override suspend fun verifyToy(id: String): UiState<ToyWithArtisan> {
        return try {
            val trimmedId = id.trim()
            val toy = toyDao.getToyById(trimmedId.uppercase())
                ?: toyDao.getToyById(trimmedId)
                ?: return UiState.Error("No toy found with ID \"$id\". Check and try again.")
            
            val artisan = artisanDao.getArtisanById(toy.artisanId)
                ?: return UiState.Error("Artisan record not found for this toy.")
            
            UiState.Success(ToyWithArtisan(toy, artisan))
        } catch (e: Exception) {
            UiState.Error("Verification failed: ${e.localizedMessage}")
        }
    }

    override suspend fun getArtisanById(id: String): UiState<Artisan> {
        return try {
            val a = artisanDao.getArtisanById(id) ?: return UiState.Error("Artisan not found.")
            UiState.Success(a)
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun getToyById(id: String): UiState<Toy> {
        return try {
            val t = toyDao.getToyById(id) ?: return UiState.Error("Toy not found.")
            UiState.Success(t)
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override fun getToyByIdFlow(id: String): Flow<UiState<Toy>> = toyDao.getToyByIdFlow(id)
        .map { toy -> 
            if (toy != null) UiState.Success(toy) 
            else UiState.Error("Toy not found") 
        }
        .distinctUntilChanged()
}
