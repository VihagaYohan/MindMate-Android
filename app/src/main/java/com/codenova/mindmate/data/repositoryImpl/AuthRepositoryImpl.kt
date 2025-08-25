package com.codenova.mindmate.data.repositoryImpl

import com.codenova.mindmate.data.mapper.UserMapper
import com.codenova.mindmate.data.remote.api.AuthApiService
import com.codenova.mindmate.data.remote.dto.LoginResponseDto
import com.codenova.mindmate.data.remote.request.LoginRequest
import com.codenova.mindmate.data.remote.request.RegisterRequest
import com.codenova.mindmate.domain.model.User
import com.codenova.mindmate.domain.repository.AuthRepository
import com.codenova.mindmate.domain.common.Result
import com.codenova.mindmate.domain.common.Success
import com.codenova.mindmate.domain.common.Error
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService,
    private val userMapper: UserMapper
) : AuthRepository {

    // user login
    override suspend fun login(email: String, password: String): LoginResponseDto {
        val response = apiService.login(LoginRequest(email, password))
        if (!response.isSuccessful) {
            throw Exception("Login failed")
        }

        val body = response.body() ?: throw Exception("Empty response")
        val cookie = response.headers()["Set-Cookie"] ?: throw Exception("No cookie found")
        val refreshToken = cookie
            ?.split(";")
            ?.firstOrNull { it.trim().startsWith("refreshToken=") }
            ?.substringAfter("=")
        return LoginResponseDto(
            accessToken = body.accessToken,
            refreshToken = refreshToken ?: ""
        )
    }

    // user register with email and password
    override suspend fun register(
        email: String,
        password: String
    ): Result<User> {
        try {
            val response = apiService.register(
                registerRequest = RegisterRequest(
                    email,
                    password
                )
            )
            if (response.isSuccessful) {
                val body = response.body()
                val payload = body?.data
                if (payload != null) {
                    val user = userMapper.toDomain(payload)
                    return Result.SuccessResult(
                        success = Success(
                            data = user,
                            status = body.status,
                            message = body.message
                        )
                    )
                } else {
                    // success but no data
                    return Result.ErrorResult(
                        error = Error(
                            status = response.code(),
                            error = response.errorBody().toString(),
                            message = response.message()
                        )
                    )
                }
            } else {
                // failed
                TODO(reason = "not yet implemented")
            }

        } catch (e: Exception) {
            return Result.ErrorResult(
                error = Error(
                    status = 500,
                    error = e.message ?: "Unknown error",
                    message = e.message ?: "Unknown error"
                )
            )
        }
    }
}