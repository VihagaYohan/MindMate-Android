package com.codenova.mindmate.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("token") val accessToken: String,
    val refreshToken: String
)
