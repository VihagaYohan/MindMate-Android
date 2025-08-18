package com.codenova.mindmate.domain.usecases.common

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        val containsLetterAndDigits = password.any { it.isDigit()} && password.any {it.isLetter()}

        if(password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password can't be blank"
            )
        }

        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password should be at least 8 characters"
            )
        }

        if(!containsLetterAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password should contain at least one letter and digit"
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}