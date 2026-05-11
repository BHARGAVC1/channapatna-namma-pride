package com.channapatna.nammapride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.channapatna.nammapride.data.local.entity.UiState
import com.channapatna.nammapride.domain.usecase.VerifyToyUseCase
import com.channapatna.nammapride.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val verifyToyUseCase: VerifyToyUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isOffline = !networkHelper.isOnline()))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onToyIdChange(v: String) {
        _uiState.value = _uiState.value.copy(toyId = v)
    }

    fun verify() {
        val id = _uiState.value.toyId.trim()
        if (id.isBlank()) {
            _uiState.value = _uiState.value.copy(verifyState = UiState.Error("Please enter a Toy ID or scan a QR code."))
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(verifyState = UiState.Loading)
            val result = verifyToyUseCase(id)
            _uiState.value = _uiState.value.copy(verifyState = result)
        }
    }

    fun reset() {
        _uiState.value = HomeUiState()
    }
}
