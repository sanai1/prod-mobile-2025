package com.kotleters.mobile.feature.company.domain.entity

data class CompanyProfile(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String,
    val categoryId: Int,
    val category: String,
    val subcategory: String
)
