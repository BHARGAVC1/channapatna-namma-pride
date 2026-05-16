package com.channapatna.nammapride.viewmodel

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.ToyWithArtisan
import com.channapatna.nammapride.data.local.entity.UiState

data class HomeUiState(
    val toyId: String = "",
    val verifyState: UiState<ToyWithArtisan> = UiState.Empty,
    val artisanSpotlight: UiState<List<Artisan>> = UiState.Loading,
    val isOffline: Boolean = false
)
