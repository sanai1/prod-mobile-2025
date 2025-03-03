package com.kotleters.mobile.common.domain

import java.time.LocalDateTime

data class Company(
    val id: String,
    val name: String,
    val offers: List<Offer>
) {
    data class Offer(
        val id: String,
        val type: OfferType,
        val title: String,
        val description: String,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val discount: Double? = null,
        val freeEvery: Long? = null,
        val bonusFromPurchase: Double? = null,
        val bonusPaymentPercent: Double? = null
    )
}
