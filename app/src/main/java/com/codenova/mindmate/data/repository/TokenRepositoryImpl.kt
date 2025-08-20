package com.codenova.mindmate.data.repository

import com.codenova.mindmate.domain.repository.TokenRepository
import com.codenova.mindmate.framework.local.TokenStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage
) : TokenRepository {
    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        tokenStorage.saveTokens(accessToken, refreshToken)
    }
    override suspend fun getAccessToken(): String? = tokenStorage.getAccessToken()
    override suspend fun getRefreshToken(): String? = tokenStorage.getRefreshToken()
    override suspend fun clearTokens() { tokenStorage.clearTokens() }
}
