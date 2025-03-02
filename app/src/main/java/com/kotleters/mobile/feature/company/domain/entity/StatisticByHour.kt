package com.kotleters.mobile.feature.company.domain.entity

import java.time.LocalDateTime

data class StatisticByHour(
    val date: LocalDateTime,
    val allAmount: Long,
    val maleAmount: Long,
    val femaleAmount: Long
)