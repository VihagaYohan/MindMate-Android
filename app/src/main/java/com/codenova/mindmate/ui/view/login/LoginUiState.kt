package com.codenova.mindmate.ui.view.login

import com.codenova.mindmate.domain.model.AuthTokens

sealed class LoginUiState {
    object Idel: LoginUiState()
    object Loading: LoginUiState()
    data class Success(val authTokens: AuthTokens): LoginUiState()
    data class Error(val message: String): LoginUiState()
}
