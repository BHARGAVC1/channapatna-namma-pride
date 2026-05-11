package com.channapatna.nammapride.viewmodel

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.UiState

data class ArtisanListUiState(
    val artisansState: UiState<List<Artisan>> = UiState.Loading
)

data class ArtisanDetailUiState(
    val artisanState: UiState<Artisan> = UiState.Loading
)
