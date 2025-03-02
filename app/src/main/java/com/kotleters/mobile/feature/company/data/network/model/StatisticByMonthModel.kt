package com.kotleters.mobile.feature.company.data.network.model

data class StatisticByMonthModel(
    val year: Long,
    val month: Long,
    val operations_amount: Long,
    val male_operations_amount: Long,
    val female_operations_amount: Long,
    val kids_op_amount: Long,
    val young_op_amount: Long,
    val middle_op_amount: Long,
    val old_op_amount: Long
)
