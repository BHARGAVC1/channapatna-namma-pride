package com.channapatna.nammapride.viewmodel

import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState

data class CatalogUiState(
    val query: String = "",
    val toysState: UiState<List<Toy>> = UiState.Loading
)
