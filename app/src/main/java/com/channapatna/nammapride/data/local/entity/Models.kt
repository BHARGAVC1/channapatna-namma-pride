package com.channapatna.nammapride.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artisans")
data class Artisan(
    @PrimaryKey val artisanId: String,
    val name: String,
    val locationText: String,
    val latitude: Double,
    val longitude: Double,
    val experienceYears: Int,
    val craftType: String,
    val bio: String,
    val photoUrl: String
)

@Entity(tableName = "toys")
data class Toy(
    @PrimaryKey val toyId: String,
    val name: String,
    val artisanId: String,
    val material: String,
    val processDescription: String,
    val imageUrl: String,
    val isAuthentic: Boolean,
    val verificationCode: String,
    val price: String,
    val category: String,
    val isFavorite: Boolean = false
)

data class ToyWithArtisan(val toy: Toy, val artisan: Artisan)

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}
