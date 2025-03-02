package com.kotleters.mobile.feature.company.domain.entity

import java.time.LocalDate

data class Statistic(
    val date: LocalDate,
    val allAmount: Int,
    val maleAmount: Int,
    val femaleAmount: Int
)
