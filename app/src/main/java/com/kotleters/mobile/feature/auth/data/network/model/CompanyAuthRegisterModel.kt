package com.kotleters.mobile.feature.auth.data.network.model

data class CompanyAuthRegisterModel(
    val name: String,
    val email: String,
    val password: String,
    val category_id: Long
)
