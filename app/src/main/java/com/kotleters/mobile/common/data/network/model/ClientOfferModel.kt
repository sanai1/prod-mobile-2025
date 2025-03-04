package com.kotleters.mobile.common.data.network.model

data class ClientOfferModel(
        val id: String,
        val company_id: String,
        val company_name: String,
        val title: String,
        val description: String,
        val type: String,
        val start_date: String,
        val end_date: String,
        val discount: Double? = null,
        val bonus_from_purchases: Double? = null,
        val bonus_payment_percent: Double? = null
)