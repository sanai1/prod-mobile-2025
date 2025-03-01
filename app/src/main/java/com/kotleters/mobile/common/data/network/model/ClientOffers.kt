package com.kotleters.mobile.common.data.network.model

data class ClientOffers(
        val id: String,
        val company_id: String,
        val company_name: String,
        val title: String,
        val description: String,
        val discount: Double,
        val start_date: String,
        val end_date: String
)