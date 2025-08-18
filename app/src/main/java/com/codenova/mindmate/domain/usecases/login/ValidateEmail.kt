package com.codenova.mindmate.domain.usecases.login

import android.util.Patterns

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Valid email is required"
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}