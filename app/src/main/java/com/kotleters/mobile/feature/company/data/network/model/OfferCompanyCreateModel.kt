package com.kotleters.mobile.feature.company.data.network.model

data class OfferCompanyCreateModel(
    val title: String,
    val description: String,
    val discount: Double,
    val startDate: String,
    val endDate: String
)
