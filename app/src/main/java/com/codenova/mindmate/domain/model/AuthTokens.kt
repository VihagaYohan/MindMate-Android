package com.codenova.mindmate.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)
