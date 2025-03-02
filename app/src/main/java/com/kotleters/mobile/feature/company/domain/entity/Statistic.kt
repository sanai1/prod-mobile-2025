package com.kotleters.mobile.feature.company.domain.entity

data class Statistic(
    val month: Month,
    val quarter: Quarter,
    val year: Year
) {
    data class Month(
        val allAmount: Long,
        val maleAmount: Long,
        val femaleAmount: Long
    )

    data class Quarter(
        val allAmount: Long,
        val maleAmount: Long,
        val femaleAmount: Long
    )

    data class Year(
        val allAmount: Long,
        val maleAmount: Long,
        val femaleAmount: Long
    )
}
