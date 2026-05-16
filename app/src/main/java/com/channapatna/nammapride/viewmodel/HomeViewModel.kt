package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.VerifyToyUseCase
import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase
import com.channapatna.nammapride.domain.usecase.GetToysUseCase
import com.channapatna.nammapride.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val verifyToyUseCase: VerifyToyUseCase,
    private val getAllArtisansUseCase: GetAllArtisansUseCase,
    private val getToysUseCase: GetToysUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _userState = MutableStateFlow(HomeUiState())
    
    val uiState: StateFlow<HomeUiState> = combine(
        _userState,
        getAllArtisansUseCase(),
        getToysUseCase(),
        networkHelper.isOnlineFlow
    ) { userState, artisans, toys, isOnline ->
        userState.copy(
            isOffline = !isOnline,
            artisanSpotlight = artisans,
            featuredToys = if (toys is UiState.Success) {
                UiState.Success(toys.data.take(6))
            } else toys
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState(isOffline = !networkHelper.isOnline()))

    fun onToyIdChange(v: String) {
        _userState.value = _userState.value.copy(toyId = v)
    }

    fun verify() {
        val id = _userState.value.toyId.trim()
        if (id.isBlank()) {
            _userState.value = _userState.value.copy(verifyState = UiState.Error("Please enter a Toy ID or scan a QR code."))
            return
        }
        viewModelScope.launch {
            _userState.update { it.copy(verifyState = UiState.Loading) }
            val result = verifyToyUseCase(id)
            
            if (result is UiState.Success) {
                val currentRecent = _userState.value.recentlyVerified
                val updatedRecent = (listOf(result.data.toy) + currentRecent)
                    .distinctBy { it.toyId }
                    .take(5)
                
                _userState.update { it.copy(
                    verifyState = result,
                    recentlyVerified = updatedRecent
                )}
            } else {
                _userState.update { it.copy(verifyState = result) }
            }
        }
    }

    fun reset() {
        _userState.value = HomeUiState()
    }
}
