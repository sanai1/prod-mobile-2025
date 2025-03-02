package com.kotleters.mobile.feature.company.presentation.anal.components

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import kotlinx.coroutines.launch

@Composable
fun AnimatedBarChart(
    data: List<Pair<String, Float>>, // Теперь передаем категорию + значение
    modifier: Modifier = Modifier
) {
    val animatedValues = remember { data.map { Animatable(0f) } }

    LaunchedEffect(data) {
        animatedValues.forEachIndexed { index, animatable ->
            launch {
                animatable.animateTo(
                    targetValue = data[index].second,
                    animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
                )
            }
        }
    }

    val maxDataValue = (data.maxOfOrNull { it.second } ?: 1f)

    val colors = mapOf(
        "Мужской" to Color.Blue,
        "Женский" to Color.Red,
        "Не указан" to Color.Gray
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val barWidth = size.width / (data.size * 2)
        val maxBarHeight = size.height * 0.8f // Оставляем место для оси Y
        val paddingLeft = 80f // Отступ для оси Y

        // Рисуем ось Y
        drawLine(
            color = Color.Black,
            start = Offset(paddingLeft, 0f),
            end = Offset(paddingLeft, maxBarHeight),
            strokeWidth = 4f
        )

        // Рисуем деления на оси Y
        val steps = 5
        for (i in 0..steps) {
            val value = (maxDataValue / steps) * i
            val yOffset = maxBarHeight - (value / maxDataValue * maxBarHeight)

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    value.toInt().toString(),
                    paddingLeft - 40f, // Чуть левее оси
                    yOffset + 10f,
                    Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 40f
                        textAlign = Paint.Align.RIGHT
                    }
                )
            }
        }

        // Рисуем столбцы
        animatedValues.forEachIndexed { index, animatable ->
            val barHeight = animatable.value / maxDataValue * maxBarHeight
            val category = data[index].first
            val barColor = colors[category] ?: Color.Gray

            drawRoundRect(
                color = barColor,
                topLeft = Offset(paddingLeft + index * 2 * barWidth, maxBarHeight - barHeight),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(20f, 20f) // Закругленные углы
            )
        }
    }
}
