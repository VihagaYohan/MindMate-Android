package com.codenova.mindmate.ui.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenova.mindmate.domain.usecases.login.LoginUseCase
import com.codenova.mindmate.domain.usecases.common.ValidateEmail
import com.codenova.mindmate.domain.usecases.common.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
): ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow<LoginUiState>(value = LoginUiState.Idel)
    val uiState: StateFlow<LoginUiState> = _uiState

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
            emailError = passwordResult.errorMessage
        )
    }

    fun onKeepLoggedInChange(checked: Boolean) {
        val editingState = (_uiState.value as? LoginUiState.Editing)?.copy()?: LoginUiState.Editing()
        _uiState.value = editingState.copy(
            keepLoggedIn = checked
        )
    }


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
