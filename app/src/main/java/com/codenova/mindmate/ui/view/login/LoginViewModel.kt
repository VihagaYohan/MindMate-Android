package com.codenova.mindmate.ui.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenova.mindmate.domain.model.AuthTokens
import com.codenova.mindmate.domain.repository.TokenRepository
import com.codenova.mindmate.domain.usecases.common.ValidateEmail
import com.codenova.mindmate.domain.usecases.common.ValidatePassword
import com.codenova.mindmate.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val tokenRepository: TokenRepository
): ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow<LoginUiState>(value = LoginUiState.Idel)
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _navigateHome = MutableSharedFlow<Unit>()
    val navigateHome = _navigateHome.asSharedFlow()

    fun onEmailChange(email: String) {
       val emailResult = validateEmail.execute(email = email)
        val editingState = (_uiState.value as? LoginUiState.Editing)?.copy()?: LoginUiState.Editing()
        _uiState.value = editingState.copy(
            email = email,
            emailError = emailResult.errorMessage
        )
    }

    fun onPasswordChange(password: String) {
        val passwordResult = validatePassword.execute(password = password)
        val editingState = (_uiState.value as? LoginUiState.Editing)?.copy()?: LoginUiState.Editing()
        _uiState.value = editingState.copy(
            password = password,
            passwordError = passwordResult.errorMessage
        )
    }

    fun onKeepLoggedInChange(checked: Boolean) {
        val editingState = (_uiState.value as? LoginUiState.Editing)?.copy()?: LoginUiState.Editing()
        _uiState.value = editingState.copy(
            keepLoggedIn = checked
        )
    }


    fun login() {
        val email = (_uiState.value as? LoginUiState.Editing)?.email ?: ""
        val password = (_uiState.value as? LoginUiState.Editing)?.password ?: ""

        if(email.isBlank() || password.isBlank()) {
            onEmailChange(email)
            onPasswordChange(password)
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
           try {
                val authTokens: AuthTokens = loginUseCase(email, password)
               if(authTokens.accessToken.isBlank() || authTokens.refreshToken.isBlank()) {
                    throw Exception("Invalid credentials")
               }

               saveTokens(authTokens)
               _navigateHome.emit(Unit)
            } catch(e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun saveTokens(authTokens: AuthTokens) {
        viewModelScope.launch {
            try {
                tokenRepository.saveTokens(
                    accessToken = authTokens.accessToken,
                    refreshToken = authTokens.refreshToken
                )
            }catch(e: Exception) {
                _uiState.value = LoginUiState.Error(message = e.message ?: "Error at saving tokens")
            }
        }
    }

    fun loadAccessToken() {
        viewModelScope.launch {
            try {
                val accessToken: String = tokenRepository.getAccessToken() ?: ""

            } catch(e: Exception) {
                _uiState.value = LoginUiState.Error(message = e.message ?: "Error at loading access token")
            }
        }
    }

    fun loadRefreshToken() {
        viewModelScope.launch {
            try {
                val refreshToken: String = tokenRepository.getRefreshToken() ?: ""
            } catch(e: Exception) {
                _uiState.value = LoginUiState.Error(message = e.message ?: "Error at loading refresh token")
            }
        }
    }

    fun clearTokens() {
        viewModelScope.launch {
            try {
                tokenRepository.clearTokens()
            }catch(e: Exception) {
                _uiState.value = LoginUiState.Error(message = e.message ?: "Error at clearing tokens")
            }
        }
    }
}
