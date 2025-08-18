package com.codenova.mindmate.domain.usecases.common

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
