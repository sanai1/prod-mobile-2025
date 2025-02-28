package com.kotleters.mobile.feature.auth.domain

sealed class UserAuth {
    data class Client(
        val firstName: String,
        val secondName: String,
        val email: String,
        val password: String
    ) : UserAuth()

    data class Company(
        val name: String,
        val email: String,
        val password: String
    ) : UserAuth()
}
