package com.codenova.mindmate.domain.usecases.auth

import com.codenova.mindmate.domain.mapper.AuthMapper
import com.codenova.mindmate.domain.model.AuthTokens
import com.codenova.mindmate.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthTokens {
        val loginResponseDto = repository.login(email, password)

        return AuthMapper().toAuthTokens(loginResponseDto)
    }
}