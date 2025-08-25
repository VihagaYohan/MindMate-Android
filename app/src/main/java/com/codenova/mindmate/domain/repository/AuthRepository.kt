package com.codenova.mindmate.domain.repository

import com.codenova.mindmate.data.remote.dto.LoginResponseDto
import com.codenova.mindmate.domain.common.Result
import com.codenova.mindmate.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponseDto
    suspend fun register(email: String, password: String): Result<User>
}