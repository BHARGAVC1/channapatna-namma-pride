package com.channapatna.nammapride.data.remote.api

import com.channapatna.nammapride.data.remote.dto.ArtisanDto
import com.channapatna.nammapride.data.remote.dto.ToyDto
import retrofit2.http.GET

interface ToyApiService {
    @GET("toys")
    suspend fun getToys(): List<ToyDto>

    @GET("artisans")
    suspend fun getArtisans(): List<ArtisanDto>

    companion object {
        const val BASE_URL = "https://api.channapatna.com/" // Placeholder URL
    }
}
