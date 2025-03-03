package com.kotleters.mobile.feature.company.data.network.model

data class OfferCompanyCreateModel(
    val type: String,
    val title: String,
    val description: String,
    val start_date: String,
    val end_date: String,
    val discount: Double? = null,
    val bonus_from_purchase: Double? = null,
    val bonus_payment_percent: Double? = null
)
