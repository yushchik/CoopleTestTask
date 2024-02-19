package com.example.coopletesttask.domain.network

sealed class ResponseStatus {
    data object Initial : ResponseStatus()
    data class Success<out T : Any>(val data: T?) : ResponseStatus()
    data object Error : ResponseStatus()
}
