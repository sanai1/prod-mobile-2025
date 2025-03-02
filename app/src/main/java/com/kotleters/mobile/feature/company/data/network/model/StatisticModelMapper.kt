package com.kotleters.mobile.feature.company.data.network.model

import com.kotleters.mobile.feature.company.domain.entity.Statistic
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StatisticModelMapper {
    fun toStatisticList(statisticList: List<StatisticModel>): List<Statistic> {
        return statisticList.map {
            Statistic(
                date = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                allAmount = it.operations_amount,
                maleAmount = it.male_operations_amount,
                femaleAmount = it.female_operations_amount
            )
        }
    }
}
