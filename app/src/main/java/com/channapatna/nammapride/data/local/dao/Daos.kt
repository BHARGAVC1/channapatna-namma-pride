package com.channapatna.nammapride.data.local.dao

import androidx.room.*
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy
import kotlinx.coroutines.flow.Flow

@Dao
interface ToyDao {
    @Query("SELECT * FROM toys")
    fun getAllToys(): Flow<List<Toy>>

    @Query("SELECT * FROM toys WHERE toyId = :id OR verificationCode = :id LIMIT 1")
    suspend fun getToyById(id: String): Toy?

    @Query("SELECT * FROM toys WHERE toyId = :id OR verificationCode = :id LIMIT 1")
    fun getToyByIdFlow(id: String): Flow<Toy?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(toys: List<Toy>)

    @Query("SELECT COUNT(*) FROM toys")
    suspend fun count(): Int

    @Query("UPDATE toys SET isFavorite = :isFav WHERE toyId = :id")
    suspend fun updateFavorite(id: String, isFav: Boolean)

    @Query("SELECT * FROM toys WHERE isFavorite = 1")
    fun getFavoriteToys(): Flow<List<Toy>>

    @Query("SELECT toyId FROM toys WHERE isFavorite = 1")
    suspend fun getFavoriteIds(): List<String>
}

@Dao
interface ArtisanDao {
    @Query("SELECT * FROM artisans")
    fun getAllArtisans(): Flow<List<Artisan>>

    @Query("SELECT * FROM artisans WHERE artisanId = :id LIMIT 1")
    suspend fun getArtisanById(id: String): Artisan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(artisans: List<Artisan>)

    @Query("SELECT COUNT(*) FROM artisans")
    suspend fun count(): Int
}
