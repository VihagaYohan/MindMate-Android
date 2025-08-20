package com.codenova.mindmate.domain.mapper

import com.codenova.mindmate.data.remote.dto.LoginResponseDto
import com.codenova.mindmate.domain.model.AuthTokens

class AuthMapper {
    fun toAuthTokens(dto: LoginResponseDto): AuthTokens {
        return AuthTokens(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken
        )
    }
}