package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.feature.company.data.network.model.StatisticByMonthModel
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import java.time.LocalDate

object StatisticModelMapper {
    fun toStatistic(statisticByMonthModelList: List<StatisticByMonthModel>): Statistic? {
        if (statisticByMonthModelList.isEmpty()) {
            return null
        }
        return Statistic(
            month = Statistic.Month(
                month = LocalDate.of(statisticByMonthModelList.first().year.toInt(), statisticByMonthModelList.first().month.toInt(), 1),
                allAmount = statisticByMonthModelList.first().operations_amount,
                maleAmount = statisticByMonthModelList.first().male_operations_amount,
                femaleAmount = statisticByMonthModelList.first().female_operations_amount,
                kidsAmount = statisticByMonthModelList.first().kids_op_amount,
                youngAmount = statisticByMonthModelList.first().young_op_amount,
                middleAmount = statisticByMonthModelList.first().middle_op_amount,
                oldAmount = statisticByMonthModelList.first().old_op_amount,
            ),
            quarter = if (statisticByMonthModelList.size == 2) {
                Statistic.Quarter(
                    monthStart = LocalDate.of(statisticByMonthModelList.first().year.toInt(), statisticByMonthModelList.first().month.toInt(), 1),
                    monthFinish = LocalDate.of(statisticByMonthModelList.last().year.toInt(), statisticByMonthModelList.last().month.toInt(), 1),
                    allAmount = statisticByMonthModelList.map {
                        it.operations_amount
                    }.sumOf { it },
                    maleAmount = statisticByMonthModelList.map {
                        it.male_operations_amount
                    }.sumOf { it },
                    femaleAmount = statisticByMonthModelList.map {
                        it.female_operations_amount
                    }.sumOf { it },
                    kidsAmount = statisticByMonthModelList.map {
                        it.kids_op_amount
                    }.sumOf { it },
                    youngAmount = statisticByMonthModelList.map {
                        it.young_op_amount
                    }.sumOf { it },
                    middleAmount = statisticByMonthModelList.map {
                        it.middle_op_amount
                    }.sumOf { it },
                    oldAmount = statisticByMonthModelList.map {
                        it.old_op_amount
                    }.sumOf { it },
                )
            } else {
                Statistic.Quarter(
                    monthStart = LocalDate.of(statisticByMonthModelList.first().year.toInt(), statisticByMonthModelList.first().month.toInt(), 1),
                    monthFinish = LocalDate.of(statisticByMonthModelList[2].year.toInt(), statisticByMonthModelList[2].month.toInt(), 1),
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
                    }.sumOf { it },
                    kidsAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.kids_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    youngAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.young_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    middleAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.middle_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    oldAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 3) {
                            statisticByMonthModel.old_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                )
            },
            year = if (statisticByMonthModelList.size < 12) {
                Statistic.Year(
                    monthStart = LocalDate.of(statisticByMonthModelList.first().year.toInt(), statisticByMonthModelList.first().month.toInt(), 1),
                    monthFinish = LocalDate.of(statisticByMonthModelList.last().year.toInt(), statisticByMonthModelList.last().month.toInt(), 1),
                    allAmount = statisticByMonthModelList.map {
                        it.operations_amount
                    }.sumOf { it },
                    maleAmount = statisticByMonthModelList.map {
                        it.male_operations_amount
                    }.sumOf { it },
                    femaleAmount = statisticByMonthModelList.map {
                        it.female_operations_amount
                    }.sumOf { it },
                    kidsAmount = statisticByMonthModelList.map {
                        it.kids_op_amount
                    }.sumOf { it },
                    youngAmount = statisticByMonthModelList.map {
                        it.young_op_amount
                    }.sumOf { it },
                    middleAmount = statisticByMonthModelList.map {
                        it.middle_op_amount
                    }.sumOf { it },
                    oldAmount = statisticByMonthModelList.map {
                        it.old_op_amount
                    }.sumOf { it },
                )
            } else {
                Statistic.Year(
                    monthStart = LocalDate.of(statisticByMonthModelList.first().year.toInt(), statisticByMonthModelList.first().month.toInt(), 1),
                    monthFinish = LocalDate.of(statisticByMonthModelList[11].year.toInt(), statisticByMonthModelList[11].month.toInt(), 1),
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
                    femaleAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.female_operations_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    kidsAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.kids_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    youngAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.young_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    middleAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.middle_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                    oldAmount = statisticByMonthModelList.mapIndexed { index, statisticByMonthModel ->
                        if (index < 12) {
                            statisticByMonthModel.old_op_amount
                        } else {
                            0
                        }
                    }.sumOf { it },
                )
            }
        )
    }
}
