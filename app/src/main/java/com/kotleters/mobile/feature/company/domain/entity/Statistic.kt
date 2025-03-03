package com.kotleters.mobile.feature.company.domain.entity

import java.time.LocalDate

data class Statistic(
    val month: Month,
    val quarter: Quarter,
    val year: Year,
) {
    data class Month(
        val month: LocalDate,
        val allAmount: Long,
        val maleAmount: Long,
        val femaleAmount: Long,
        val kidsAmount: Long,
        val youngAmount: Long,
        val middleAmount: Long,
        val oldAmount: Long,
    )

    data class Quarter(
        val monthStart: LocalDate,
        val monthFinish: LocalDate,
        val allAmount: Long,
        val maleAmount: Long,
        val femaleAmount: Long,
        val kidsAmount: Long,
        val youngAmount: Long,
        val middleAmount: Long,
        val oldAmount: Long,
    )

    data class Year(
        val monthStart: LocalDate,
        val monthFinish: LocalDate,
        val allAmount: Long,
        val maleAmount: Long,
        val femaleAmount: Long,
        val kidsAmount: Long,
        val youngAmount: Long,
        val middleAmount: Long,
        val oldAmount: Long,
    )
}
