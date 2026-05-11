package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase
import com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase
import com.channapatna.nammapride.domain.usecase.GetToyByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToyDetailViewModel @Inject constructor(
    private val getToyByIdUseCase: GetToyByIdUseCase,
    private val getArtisanByIdUseCase: GetArtisanByIdUseCase,
    private val toggleFavoriteUseCase: com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun load(toyId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(toyState = UiState.Loading, artisanState = UiState.Loading)
            val result = getToyByIdUseCase(toyId)
            _uiState.value = _uiState.value.copy(toyState = result)
            if (result is UiState.Success) {
                val artisanResult = getArtisanByIdUseCase(result.data.artisanId)
                _uiState.value = _uiState.value.copy(artisanState = artisanResult)
            }
        }
    }

    fun toggleFavorite(toy: com.channapatna.nammapride.data.local.entity.Toy) {
        viewModelScope.launch {
            toggleFavoriteUseCase(toy.toyId, toy.isFavorite)
            // Refresh state
            val result = getToyByIdUseCase(toy.toyId)
            _uiState.value = _uiState.value.copy(toyState = result)
        }
    }
}

@HiltViewModel
class ArtisanViewModel @Inject constructor(
    private val getAllArtisansUseCase: GetAllArtisansUseCase,
    private val getArtisanByIdUseCase: GetArtisanByIdUseCase
) : ViewModel() {
    val artisansState: StateFlow<UiState<List<com.channapatna.nammapride.data.local.entity.Artisan>>> = getAllArtisansUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    private val _selectedState = MutableStateFlow(ArtisanDetailUiState())
    val selectedState: StateFlow<ArtisanDetailUiState> = _selectedState.asStateFlow()

    fun loadArtisan(id: String) {
        viewModelScope.launch {
            _selectedState.value = _selectedState.value.copy(artisanState = UiState.Loading)
            val result = getArtisanByIdUseCase(id)
            _selectedState.value = _selectedState.value.copy(artisanState = result)
        }
    }
}
