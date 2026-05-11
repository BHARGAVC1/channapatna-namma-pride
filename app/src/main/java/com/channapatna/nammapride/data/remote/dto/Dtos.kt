package com.channapatna.nammapride.data.remote.dto

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy

data class ToyDto(
    val toyId: String,
    val name: String,
    val artisanId: String,
    val material: String,
    val processDescription: String,
    val imageUrl: String,
    val isAuthentic: Boolean,
    val verificationCode: String,
    val price: String,
    val category: String
)

data class ArtisanDto(
    val artisanId: String,
    val name: String,
    val locationText: String,
    val latitude: Double,
    val longitude: Double,
    val experienceYears: Int,
    val craftType: String,
    val bio: String,
    val photoUrl: String
)

fun ToyDto.toEntity(): Toy = Toy(
    toyId = toyId,
    name = name,
    artisanId = artisanId,
    material = material,
    processDescription = processDescription,
    imageUrl = imageUrl,
    isAuthentic = isAuthentic,
    verificationCode = verificationCode,
    price = price,
    category = category,
    isFavorite = false // Default to false when fetching from remote
)

fun ArtisanDto.toEntity(): Artisan = Artisan(
    artisanId = artisanId,
    name = name,
    locationText = locationText,
    latitude = latitude,
    longitude = longitude,
    experienceYears = experienceYears,
    craftType = craftType,
    bio = bio,
    photoUrl = photoUrl
)
