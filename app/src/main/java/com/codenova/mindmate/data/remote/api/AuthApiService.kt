package com.codenova.mindmate.data.remote.api

import com.codenova.mindmate.data.remote.dto.LoginResponseDto
import com.codenova.mindmate.data.remote.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponseDto>
}