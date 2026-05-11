package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.GetToysUseCase
import com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getToysUseCase: GetToysUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _query = MutableStateFlow("")

    val uiState: StateFlow<CatalogUiState> = combine(getToysUseCase(), _query) { state, q ->
        val filtered = if (state is UiState.Success && q.isNotBlank()) {
            val f = state.data.filter {
                it.name.contains(q, true) ||
                        it.category.contains(q, true) ||
                        it.material.contains(q, true)
            }
            if (f.isEmpty()) UiState.Empty else UiState.Success(f)
        } else {
            state
        }
        CatalogUiState(q, filtered)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CatalogUiState())

    fun onSearchChange(q: String) {
        _query.value = q
    }

    fun onFavoriteClick(toy: Toy) {
        viewModelScope.launch {
            toggleFavoriteUseCase(toy.toyId, toy.isFavorite)
        }
    }
}
