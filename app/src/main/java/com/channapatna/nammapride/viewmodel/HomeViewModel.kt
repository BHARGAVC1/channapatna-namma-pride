package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.VerifyToyUseCase
import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase
import com.channapatna.nammapride.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val verifyToyUseCase: VerifyToyUseCase,
    private val getAllArtisansUseCase: GetAllArtisansUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _userState = MutableStateFlow(HomeUiState())
    
    val uiState: StateFlow<HomeUiState> = combine(
        _userState,
        getAllArtisansUseCase(),
        networkHelper.isOnlineFlow
    ) { userState, artisans, isOnline ->
        userState.copy(
            isOffline = !isOnline,
            artisanSpotlight = artisans
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
            _userState.value = _userState.value.copy(verifyState = UiState.Loading)
            val result = verifyToyUseCase(id)
            _userState.value = _userState.value.copy(verifyState = result)
        }
    }

    fun reset() {
        _userState.value = HomeUiState()
    }
}
