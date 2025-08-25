package com.codenova.mindmate.data.remote.api

import com.codenova.mindmate.data.remote.common.ApiResponse
import com.codenova.mindmate.data.remote.dto.LoginResponseDto
import com.codenova.mindmate.data.remote.dto.RegisterResponseDto
import com.codenova.mindmate.data.remote.request.LoginRequest
import com.codenova.mindmate.data.remote.request.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponseDto>

    @POST(value = "/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<ApiResponse<RegisterResponseDto>>
}