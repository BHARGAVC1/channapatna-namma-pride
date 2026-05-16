package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase
import com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase
import com.channapatna.nammapride.domain.usecase.GetToyByIdUseCase
import com.channapatna.nammapride.domain.usecase.GetToyByIdFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToyDetailViewModel @Inject constructor(
    private val getToyByIdUseCase: GetToyByIdUseCase,
    private val getToyByIdFlowUseCase: GetToyByIdFlowUseCase,
    private val getArtisanByIdUseCase: GetArtisanByIdUseCase,
    private val toggleFavoriteUseCase: com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase
) : ViewModel() {
    private val _toyId = MutableStateFlow<String?>(null)
    
    val uiState: StateFlow<DetailUiState> = _toyId.flatMapLatest { id ->
        if (id == null) kotlinx.coroutines.flow.flowOf(DetailUiState())
        else {
            val toyFlow = getToyByIdFlowUseCase(id)
            kotlinx.coroutines.flow.combine(toyFlow, MutableStateFlow<UiState<com.channapatna.nammapride.data.local.entity.Artisan>>(UiState.Loading)) { toyState, artisanState ->
                // Initial artisan load if toy succeeds
                if (toyState is UiState.Success && _artisanState.value is UiState.Loading) {
                    loadArtisan(toyState.data.artisanId)
                }
                DetailUiState(toyState, _artisanState.value)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DetailUiState())

    private val _artisanState = MutableStateFlow<UiState<com.channapatna.nammapride.data.local.entity.Artisan>>(UiState.Loading)

    private fun loadArtisan(artisanId: String) {
        viewModelScope.launch {
            _artisanState.value = getArtisanByIdUseCase(artisanId)
        }
    }

    fun load(toyId: String) {
        _toyId.value = toyId
    }

    fun toggleFavorite(toy: com.channapatna.nammapride.data.local.entity.Toy) {
        viewModelScope.launch {
            toggleFavoriteUseCase(toy.toyId, toy.isFavorite)
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
