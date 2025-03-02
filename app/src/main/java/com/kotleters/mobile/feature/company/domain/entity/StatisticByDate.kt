package com.kotleters.mobile.feature.company.domain.entity

import java.time.LocalDate

data class StatisticByDate(
    val date: LocalDate,
    val allAmount: Long,
    val maleAmount: Long,
    val femaleAmount: Long
)
