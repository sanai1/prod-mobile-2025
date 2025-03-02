package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.feature.company.data.network.model.StatisticByDateModel
import com.kotleters.mobile.feature.company.data.network.model.StatisticByHourModel
import com.kotleters.mobile.feature.company.data.network.model.StatisticByMonthModel
import com.kotleters.mobile.feature.company.domain.entity.StatisticByDate
import com.kotleters.mobile.feature.company.domain.entity.StatisticByHour
import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object StatisticModelMapper {
    fun toStatisticByDateList(statisticList: List<StatisticByDateModel>): List<StatisticByDate> {
        return statisticList.map {
            StatisticByDate(
                date = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                allAmount = it.operations_amount,
                maleAmount = it.male_operations_amount,
                femaleAmount = it.female_operations_amount
            )
        }
    }

    fun toStatisticByHourList(statisticByHourList: List<StatisticByHourModel>): List<StatisticByHour> {
        return statisticByHourList.map {
            StatisticByHour(
                date = LocalDateTime.of(
                    LocalDate.parse(it.date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.of(it.hour.toInt(), 0)
                ),
                allAmount = it.operations_amount,
                maleAmount = it.male_operations_amount,
                femaleAmount = it.female_operations_amount
            )
        }
    }
    
    fun toStatisticByMonthList(statisticByMonthList: List<StatisticByMonthModel>): List<StatisticByMonth> {
        return statisticByMonthList.map { 
            StatisticByMonth(
                date = LocalDate.of(it.year.toInt(), it.month.toInt(), 0),
                allAmount = it.operations_amount,
                maleAmount = it.male_operations_amount,
                femaleAmount = it.female_operations_amount
            )
        }
    }
}
