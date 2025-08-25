package com.codenova.mindmate.domain.common

data class Error(
    val status: Int,
    val error: String,
    val message: String
)
