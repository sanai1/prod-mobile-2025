package com.kotleters.mobile.common.domain

import java.time.LocalDateTime

data class Company(
    val id: String,
    val name: String,
    val discountList: List<Discount>,
    val freeEveryList: List<FreeEvery>,
    val bonus: Bonus
) {
    data class Discount(
        val id: String,
        val title: String,
        val description: String,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val discount: Double
    )

    data class FreeEvery(
        val id: String,
        val title: String,
        val description: String,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val freeEvery: Long
    )

    data class Bonus(
        val id: String,
        val title: String,
        val description: String,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val bonusFromPurchase: Double,
        val bonusPaymentPercent: Double
    )
}
