package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.feature.company.data.network.model.StatisticByMonthModel
import com.kotleters.mobile.feature.company.domain.entity.Statistic

object StatisticModelMapper {
    fun toStatistic(statisticByMonthModelList: List<StatisticByMonthModel>): Statistic? {
        if (statisticByMonthModelList.isEmpty()) {
            return null
        }
        return Statistic(
            month = Statistic.Month(
                allAmount = statisticByMonthModelList[0].operations_amount,
                maleAmount = statisticByMonthModelList[0].male_operations_amount,
                femaleAmount = statisticByMonthModelList[0].female_operations_amount
            ),
            quarter = if (statisticByMonthModelList.size == 2) {
                Statistic.Quarter(
                    allAmount = statisticByMonthModelList.map {
                        it.operations_amount
                    }.sumOf { it },
                    maleAmount = statisticByMonthModelList.map {
                        it.male_operations_amount
                    }.sumOf { it },
                    femaleAmount = statisticByMonthModelList.map {
                        it.female_operations_amount
                    }.sumOf { it }
                )
            } else {
                Statistic.Quarter(
                    allAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    maleAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.male_operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    femaleAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.female_operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it }
                )
            },
            year = if (statisticByMonthModelList.size < 12) {
                Statistic.Year(
                    allAmount = statisticByMonthModelList.map {
                        it.operations_amount
                    }.sumOf { it },
                    maleAmount = statisticByMonthModelList.map {
                        it.male_operations_amount
                    }.sumOf { it },
                    femaleAmount = statisticByMonthModelList.map {
                        it.female_operations_amount
                    }.sumOf { it }
                )
            } else {
                Statistic.Year(
                    allAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    maleAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.male_operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    femaleAmount =statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.female_operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it }
                )
            }
        )
    }
}
