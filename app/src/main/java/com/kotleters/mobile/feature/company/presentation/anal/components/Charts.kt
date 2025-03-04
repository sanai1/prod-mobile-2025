package com.kotleters.mobile.feature.company.presentation.anal.components

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import kotlinx.coroutines.launch

val genderLegend =
    listOf(Pair("male", "Мужской"), Pair("female", "Женский"), Pair("other", "Не указан"))
val ageLegend = listOf(
    Pair("kids", "Дети (до 17)"), Pair("young", "Молодые (18-34)"),
    Pair("middle", "Средний возраст (35-59)"), Pair("old", "Пожилые (60+)")
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedBarChart(
    data: List<Statistic>,
    yAxisType: String, // "gender" или "age"
    period: String, // "month", "quarter", "year"
    modifier: Modifier = Modifier
) {
    val previousMaxValue = remember { mutableStateOf(0f) }
    val animatedValues = remember { data.map { Animatable(0f) } }

    LaunchedEffect(data, yAxisType, period) {
        val newMaxValue = (data.maxOfOrNull { getTotalAmount(it, period) } ?: 1).toFloat()
        val scaleFactor = previousMaxValue.value / newMaxValue
        val startValue = if (scaleFactor > 1.5f) newMaxValue * 0.75f else previousMaxValue.value
        previousMaxValue.value = newMaxValue

        animatedValues.forEachIndexed { index, animatable ->
            launch {
                animatable.snapTo(startValue)
                animatable.animateTo(
                    targetValue = getTotalAmount(data[index], period).toFloat(),
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                )
            }
        }
    }

    val maxDataValue = previousMaxValue.value

    val categoryColors = if (yAxisType == "gender") {
        mapOf(
            "male" to Color(0xFFE86666),
            "female" to Color(0xFF6775F4),
            "other" to Color(0xFF66E8B6)
        )
    } else {
        mapOf(
            "kids" to Color(0xFFFFC107),
            "young" to Color(0xFF29B6F6),
            "middle" to Color(0xFF66BB6A),
            "old" to Color(0xFF8E24AA)
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            val paddingLeft = 220f // Увеличен отступ между осью Y и столбцами
            val barWidth = size.width / (data.size * 1.8f) // Сделали столбцы шире
            val barSpacing = size.width / (data.size * 8f) // Уменьшили расстояние между столбцами
            val maxBarHeight = size.height * 0.75f

//            // Ось Y
//            drawLine(
//                color = Color(0xFFAAAAAA),
//                start = Offset(paddingLeft, 0f),
//                end = Offset(paddingLeft, maxBarHeight),
//                strokeWidth = 3f
//            )

            // Деления Y
            val steps = 5
            for (i in 0..steps) {
                val value = (((maxDataValue / steps) * i).toInt() / 100) * 100
                val yOffset = maxBarHeight - (value / maxDataValue * maxBarHeight)
                drawContext.canvas.nativeCanvas.drawText(
                    value.toString(),
                    paddingLeft - 60f,
                    yOffset + 10f,
                    Paint().apply {
                        color = android.graphics.Color.parseColor("#B0B0B0")
                        textSize = 40f
                        textAlign = Paint.Align.RIGHT
                    }
                )
            }

            // Рисуем столбцы
            animatedValues.forEachIndexed { index, animatable ->
                val stat = data[index]
                val values = getValuesByType(stat, yAxisType, period)

                val xOffset = paddingLeft + index * (barWidth + barSpacing)
                val yBase = maxBarHeight
                var lastHeight = 0f

                values.forEach { (category, amount) ->
                    val height = (animatable.value * amount / getTotalAmount(
                        stat,
                        period
                    )) / maxDataValue * maxBarHeight
                    val color = categoryColors[category] ?: Color.Gray

                    drawRoundRect(
                        color = color,
                        topLeft = Offset(xOffset, yBase - lastHeight - height),
                        size = Size(barWidth, height),
                        cornerRadius = CornerRadius(20f, 20f)
                    )

                    lastHeight += height
                }
            }
        }


        // Легенда (динамически меняется в зависимости от типа оси Y)
        FlowRow(
            modifier = Modifier.width(300.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            categoryColors.forEach { (category, color) ->
                val title =
                    if (category in ageLegend.map { it.first }) ageLegend.first { it.first == category }.second
                    else genderLegend.first { it.first == category }.second
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(color, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(title, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}


fun getTotalAmount(stat: Statistic, period: String): Long {
    return when (period) {
        "month" -> stat.month.allAmount
        "quarter" -> stat.quarter.allAmount
        "year" -> stat.year.allAmount
        else -> 1L
    }
}

fun getValuesByType(stat: Statistic, yAxisType: String, period: String): Map<String, Long> {
    return when (yAxisType) {
        "gender" -> mapOf(
            "male" to when (period) {
                "month" -> stat.month.maleAmount
                "quarter" -> stat.quarter.maleAmount
                "year" -> stat.year.maleAmount
                else -> 0
            },
            "female" to when (period) {
                "month" -> stat.month.femaleAmount
                "quarter" -> stat.quarter.femaleAmount
                "year" -> stat.year.femaleAmount
                else -> 0
            },
            "other" to when (period) {
                "month" -> stat.month.allAmount - (stat.month.maleAmount + stat.month.femaleAmount)
                "quarter" -> stat.quarter.allAmount - (stat.quarter.maleAmount + stat.quarter.femaleAmount)
                "year" -> stat.year.allAmount - (stat.year.maleAmount + stat.year.femaleAmount)
                else -> 0
            }
        )

        "age" -> mapOf(
            "kids" to when (period) {
                "month" -> stat.month.kidsAmount
                "quarter" -> stat.quarter.kidsAmount
                "year" -> stat.year.kidsAmount
                else -> 0
            },
            "young" to when (period) {
                "month" -> stat.month.youngAmount
                "quarter" -> stat.quarter.youngAmount
                "year" -> stat.year.youngAmount
                else -> 0
            },
            "middle" to when (period) {
                "month" -> stat.month.middleAmount
                "quarter" -> stat.quarter.middleAmount
                "year" -> stat.year.middleAmount
                else -> 0
            },
            "old" to when (period) {
                "month" -> stat.month.oldAmount
                "quarter" -> stat.quarter.oldAmount
                "year" -> stat.year.oldAmount
                else -> 0
            }
        )

        else -> emptyMap()
    }
}

