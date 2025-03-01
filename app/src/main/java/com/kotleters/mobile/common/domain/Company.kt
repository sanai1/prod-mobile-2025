package com.kotleters.mobile.common.domain

import java.time.LocalDateTime

data class Company(
    val id: String,
    val name: String,
    val offers: List<Offer>
) {
    data class Offer(
        val id: String,
        val title: String,
        val description: String,
        val discount: Double,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime
    )
}
