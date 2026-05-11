package com.channapatna.nammapride.viewmodel

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState

data class DetailUiState(
    val toyState: UiState<Toy> = UiState.Loading,
    val artisanState: UiState<Artisan> = UiState.Loading
)
