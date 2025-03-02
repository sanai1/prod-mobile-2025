package com.kotleters.mobile.feature.company.data.network.model

data class StatisticByMonthModel(
    val year: Long,
    val month: Long,
    val operations_amount: Long,
    val male_operations_amount: Long,
    val female_operations_amount: Long
)
