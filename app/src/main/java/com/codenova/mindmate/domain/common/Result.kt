package com.codenova.mindmate.domain.common

sealed class Result<out T> {
    data class SuccessResult<T>(val success: Success<T>): Result<T>()
    data class ErrorResult(val error: Error): Result<Nothing>()
}