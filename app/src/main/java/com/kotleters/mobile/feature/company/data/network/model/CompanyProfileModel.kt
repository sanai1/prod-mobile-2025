package com.kotleters.mobile.feature.company.data.network.model

data class CompanyProfileModel(
    val id: String,
    val name: String,
    val email: String,
    val category_id: Int,
    val category: String,
    val subcategory: String
)
