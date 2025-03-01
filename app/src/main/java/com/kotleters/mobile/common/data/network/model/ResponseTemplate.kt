package com.kotleters.mobile.common.data.network.model

sealed class ResponseTemplate<T> {
    data class Success<T>(
        val data: T
    ) : ResponseTemplate<T>()

    data class Error<T>(
        val message: String
    ) : ResponseTemplate<T>()
}


