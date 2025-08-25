package com.codenova.mindmate.ui.view.register

import com.codenova.mindmate.domain.model.User
import com.codenova.mindmate.domain.common.Error

sealed class RegisterUiState {
    object Idel: RegisterUiState()
    object Loading: RegisterUiState()

    data class SuccessResult(val user: User?): RegisterUiState()
    data class ErrorResult(val error: Error?): RegisterUiState()

    data class Editing(
        val email: String = "",
        val password: String = "",
        val emailError: String? = null,
        val passwordError: String? = null,
        val keepLoggedIn: Boolean = false
    ): RegisterUiState()
}