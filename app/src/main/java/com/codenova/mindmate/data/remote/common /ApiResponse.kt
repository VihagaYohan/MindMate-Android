package com.codenova.mindmate.data.remote.common

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerializedName("status") val status: Int,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "data") val data: T
)
