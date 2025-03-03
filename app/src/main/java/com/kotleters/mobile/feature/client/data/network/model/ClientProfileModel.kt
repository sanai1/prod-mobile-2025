package com.kotleters.mobile.feature.client.data.network.model

data class ClientProfileModel(
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val bonus: Double,
    val age: Int? = null,
    val gender: String? = null
)
