package com.kotleters.mobile.feature.client.domain.entity

data class ClientProfile(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val bonus: Double,
    val targetInfo: TargetInfo?,
)
