package com.channapatna.nammapride.data.remote.dto

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy
import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toToy(): Toy? {
    return try {
        Toy(
            toyId              = getString("toyId") ?: return null,
            name               = getString("name") ?: return null,
            artisanId          = getString("artisanId") ?: return null,
            material           = getString("material") ?: "",
            processDescription = getString("processDescription") ?: "",
            imageUrl           = getString("imageUrl") ?: "",
            isAuthentic        = getBoolean("isAuthentic") ?: false,
            verificationCode   = getString("verificationCode") ?: "",
            price              = getString("price") ?: "",
            category           = getString("category") ?: "",
            isFavorite         = false  // Room owns this field
        )
    } catch (e: Exception) {
        null
    }
}

fun DocumentSnapshot.toArtisan(): Artisan? {
    return try {
        Artisan(
            artisanId       = getString("artisanId") ?: return null,
            name            = getString("name") ?: return null,
            locationText    = getString("locationText") ?: "",
            latitude        = getDouble("latitude") ?: 0.0,
            longitude       = getDouble("longitude") ?: 0.0,
            experienceYears = getLong("experienceYears")?.toInt() ?: 0,
            craftType       = getString("craftType") ?: "",
            bio             = getString("bio") ?: "",
            photoUrl        = getString("photoUrl") ?: ""
        )
    } catch (e: Exception) {
        null
    }
}
