package com.codenova.mindmate.domain.repository

import com.codenova.mindmate.data.remote.dto.LoginResponseDto

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponseDto
}