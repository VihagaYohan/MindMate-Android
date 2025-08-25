package com.codenova.mindmate.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName (value = "email") val email: String,
    @SerialName (value = "password") val password: String
)
