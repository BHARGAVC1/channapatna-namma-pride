package com.channapatna.nammapride.data.repository

import com.channapatna.nammapride.data.model.SampleData
import com.channapatna.nammapride.data.remote.dto.toFirestoreMap
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreSeeder @Inject constructor(
    private val db: FirebaseFirestore
) {
    suspend fun seedIfEmpty() {
        val snap = db.collection("toys").limit(1).get().await()
        if (!snap.isEmpty) return  // already seeded

        val batch = db.batch()

        SampleData.toys.forEach { toy ->
            val ref = db.collection("toys").document(toy.toyId)
            batch.set(ref, toy.toFirestoreMap())
        }
        SampleData.artisans.forEach { artisan ->
            val ref = db.collection("artisans").document(artisan.artisanId)
            batch.set(ref, artisan.toFirestoreMap())
        }

        batch.commit().await()
    }
}
