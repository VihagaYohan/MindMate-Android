package com.codenova.mindmate.data.repository

import com.codenova.mindmate.data.remote.api.AuthApiService
import com.codenova.mindmate.data.remote.dto.LoginResponseDto
import com.codenova.mindmate.data.remote.request.LoginRequest
import com.codenova.mindmate.domain.repository.AuthRepository
import okhttp3.Cookie
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService
): AuthRepository  {

    // user login
    override suspend fun login(email: String, password: String): LoginResponseDto {
        val response = apiService.login(LoginRequest(email, password))
        if(!response.isSuccessful) {
            throw Exception("Login failed")
        }

        val body = response.body() ?: throw Exception("Empty response")
        val cookie = response.headers()["Set-Cookie"] ?: throw Exception("No cookie found")
        val refreshToken = cookie
            ?.split(";")
            ?.firstOrNull{ it.trim().startsWith("refreshToken=")}
            ?.substringAfter("=")
        return LoginResponseDto(
            accessToken = body.accessToken,
            refreshToken = refreshToken?:"")
    }
}