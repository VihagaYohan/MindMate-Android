package com.codenova.mindmate.domain.usecases.auth

import com.codenova.mindmate.domain.model.User
import com.codenova.mindmate.domain.repository.AuthRepository
import com.codenova.mindmate.domain.common.Result
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        val result =  repository.register(email, password)
        return result
    }
}