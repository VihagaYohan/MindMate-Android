package com.codenova.mindmate.domain.usecases.login

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
