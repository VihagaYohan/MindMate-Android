package com.codenova.mindmate.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    @SerializedName(value = "id") val id: Int,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "role") val role: String
)
