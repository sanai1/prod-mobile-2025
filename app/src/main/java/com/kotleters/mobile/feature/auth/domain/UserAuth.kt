package com.kotleters.mobile.feature.auth.domain

sealed class UserAuth {
    data class Client(
        val firstName: String? = null,
        val secondName: String? = null,
        val email: String,
        val password: String
    ) : UserAuth()

    data class Company(
        val email: String,
        val password: String,
        val category: String? = null,
        val subcategory: String? = null,
        val name: String? = null,
    ) : UserAuth()
}
