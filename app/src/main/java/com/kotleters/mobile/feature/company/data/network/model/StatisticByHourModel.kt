package com.kotleters.mobile.feature.company.data.network.model

data class StatisticByHourModel(
    val date: String,
    val hour: Long,
    val operations_amount: Long,
    val male_operations_amount: Long,
    val female_operations_amount: Long
)
