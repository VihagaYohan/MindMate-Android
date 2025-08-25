package com.codenova.mindmate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenova.mindmate.domain.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
): ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow<String>(value = "")
    val uiState: StateFlow<String> = _uiState

    init {
        loadAccessToken()
    }

    fun loadAccessToken(){
        viewModelScope.launch {
            try {
                val accessToken: String = tokenRepository.getAccessToken() ?: ""
                val result = accessToken
                _uiState.value = accessToken
            } catch(e: Exception) {
                _uiState.value = ""
            }
        }
    }
}