package com.codenova.mindmate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenova.mindmate.domain.common.Result
import com.codenova.mindmate.domain.model.User
import com.codenova.mindmate.domain.usecases.auth.RegisterUseCase
import com.codenova.mindmate.domain.usecases.common.ValidateEmail
import com.codenova.mindmate.domain.usecases.common.ValidatePassword
import com.codenova.mindmate.presentation.model.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
) : ViewModel() {

    // UI state exposed to the UI
    private val _uiState = MutableStateFlow<RegisterUiState>(value = RegisterUiState.Idel)
    val uiState: StateFlow<RegisterUiState> = _uiState

    private val _navigateHome = MutableSharedFlow<Unit>()
    val navigateHome = _navigateHome.asSharedFlow()

    fun onEmailChange(email: String) {
        val emailResult = validateEmail.execute(email = email)
        val editingState =
            (_uiState.value as? RegisterUiState.Editing)?.copy() ?: RegisterUiState.Editing()
        _uiState.value = editingState.copy(
            email = email,
            emailError = emailResult.errorMessage
        )
    }

    fun onPasswordChange(password: String) {
        val passwordResult = validatePassword.execute(password = password)
        val editingState =
            (_uiState.value as? RegisterUiState.Editing)?.copy() ?: RegisterUiState.Editing()
        _uiState.value = editingState.copy(
            password = password,
            passwordError = passwordResult.errorMessage
        )
    }

    fun onKeepLoggedInChange(checked: Boolean) {
        val editingState =
            (_uiState.value as? RegisterUiState.Editing)?.copy() ?: RegisterUiState.Editing()
        _uiState.value = editingState.copy(
            keepLoggedIn = checked
        )
    }

    fun register() {
        val email = (uiState.value as? RegisterUiState.Editing)?.email ?: "vihagayohan94@gmail1.com"
        val password = (uiState.value as? RegisterUiState.Editing)?.password ?: "Batman"

        if (email.isBlank() || password.isBlank()) {
            onEmailChange(email)
            onPasswordChange(password)
            return
        }

        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            val result: Result<User> = registerUseCase(email, password)
            when (result) {
                is Result.SuccessResult -> {
                    val successState = (_uiState.value as? RegisterUiState.SuccessResult)?.copy()
                        ?: RegisterUiState.SuccessResult(
                            user = null
                        )
                    _uiState.value = successState.copy(user = result.success.data)
                    _navigateHome.emit(Unit)
                }

                is Result.ErrorResult -> {
                    val errorState =
                        (_uiState.value as? RegisterUiState.ErrorResult)?.copy()
                            ?: RegisterUiState.ErrorResult(
                                error = null
                            )
                    _uiState.value = errorState.copy(error = result.error)
                }
            }
        }
    }
}