package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.GetFavoriteToysUseCase
import com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteToysUseCase: GetFavoriteToysUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val favoritesState: StateFlow<UiState<List<Toy>>> = getFavoriteToysUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    fun onToggleFavorite(toy: Toy) {
        viewModelScope.launch {
            toggleFavoriteUseCase(toy.toyId, toy.isFavorite)
        }
    }
}
