package com.codenova.mindmate.domain.common

data class Success<out T>(
    val status: Int = 200,
    val message: String = "Success",
    val data: T
)