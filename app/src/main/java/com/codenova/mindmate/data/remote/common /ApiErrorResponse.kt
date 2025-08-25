package com.codenova.mindmate.data.remote.common

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    @SerializedName(value = "status") val status: Int,
    @SerializedName(value = "error") val error: String,
    @SerializedName(value = "message") val message: String
)
