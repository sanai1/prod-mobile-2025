package com.kotleters.mobile.feature.company.data.network.model

data class StatisticByDateModel(
    val date: String,
    val operations_amount: Long,
    val male_operations_amount: Long,
    val female_operations_amount: Long
)
