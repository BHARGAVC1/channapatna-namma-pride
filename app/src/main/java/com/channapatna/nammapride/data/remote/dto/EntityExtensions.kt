package com.channapatna.nammapride.data.remote.dto

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy

fun Toy.toFirestoreMap(): Map<String, Any?> = mapOf(
    "toyId"              to toyId,
    "name"               to name,
    "artisanId"          to artisanId,
    "material"           to material,
    "processDescription" to processDescription,
    "imageUrl"           to imageUrl,
    "isAuthentic"        to isAuthentic,
    "verificationCode"   to verificationCode,
    "price"              to price,
    "category"           to category
)

fun Artisan.toFirestoreMap(): Map<String, Any?> = mapOf(
    "artisanId"       to artisanId,
    "name"            to name,
    "locationText"    to locationText,
    "latitude"        to latitude,
    "longitude"       to longitude,
    "experienceYears" to experienceYears,
    "craftType"       to craftType,
    "bio"             to bio,
    "photoUrl"        to photoUrl
)
