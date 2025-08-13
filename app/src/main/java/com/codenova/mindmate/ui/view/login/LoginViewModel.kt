package com.codenova.mindmate.ui.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenova.mindmate.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idel)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                val authTokens = loginUseCase(email, password)
                _uiState.value = LoginUiState.Success(authTokens)
            } catch(e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
