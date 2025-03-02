package com.kotleters.mobile.feature.company.presentation.anal.components

import android.graphics.Paint
import android.graphics.Typeface
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
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import kotlinx.coroutines.launch

@Composable
fun AnimatedBarChart(
    data: List<Statistic>,
    modifier: Modifier = Modifier
) {
//    val animatedValues = remember { data.map { Animatable(0f) } }
//
//    LaunchedEffect(data) {
//        animatedValues.forEachIndexed { index, animatable ->
//            launch {
//                animatable.animateTo(
//                    targetValue = data[index].allAmount.toFloat(),
//                    animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
//                )
//            }
//        }
//    }
//
//    val maxDataValue = (data.maxOfOrNull { it.allAmount } ?: 1).toFloat()
//
//    val colorMale = Color(0xFFE86666)
//    val colorFemale = Color(0xFF6775F4)
//    val colorOther = Color(0xFF66E8B6)
//
//    val textPaint = Paint().apply {
//        color = android.graphics.Color.parseColor("#B0B0B0") // Светло-серый текст
//        textSize = 40f
//        textAlign = Paint.Align.CENTER
//        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//    }
//
//    Canvas(modifier = modifier.fillMaxSize()) {
//        val barWidth = size.width / (data.size * 2.5f) // Чтобы был эффект наложения
//        val maxBarHeight = size.height * 0.75f // Оставляем место сверху
//        val paddingLeft = 160f // Больше места для оси Y
//
//        // Ось Y (возраст)
//        drawLine(
//            color = Color(0xFFAAAAAA), // Серый оттенок
//            start = Offset(paddingLeft, 0f),
//            end = Offset(paddingLeft, maxBarHeight),
//            strokeWidth = 3f
//        )
//
//        // Деления по Y
//        val steps = 5
//        for (i in 0..steps) {
//            val value = (maxDataValue / steps) * i
//            val yOffset = maxBarHeight - (value / maxDataValue * maxBarHeight)
//
//            drawContext.canvas.nativeCanvas.drawText(
//                "${(20 + i * 10)} лет", // Возрастные категории
//                paddingLeft - 60f,
//                yOffset + 10f,
//                textPaint
//            )
//        }
//
//        // Отрисовка столбцов
//        animatedValues.forEachIndexed { index, animatable ->
//            val stat = data[index]
//
//            val maleHeight = (animatable.value * stat.maleAmount / stat.allAmount) / maxDataValue * maxBarHeight
//            val femaleHeight = (animatable.value * stat.femaleAmount / stat.allAmount) / maxDataValue * maxBarHeight
//            val otherHeight = (animatable.value * (stat.allAmount - stat.maleAmount - stat.femaleAmount) / stat.allAmount) / maxDataValue * maxBarHeight
//
//            val xOffset = paddingLeft + index * 2 * barWidth - barWidth / 4 // Смещаем для наложения
//            val yBase = maxBarHeight
//
//            // "Другое"
//            drawRoundRect(
//                color = colorOther,
//                topLeft = Offset(xOffset, yBase - maleHeight - femaleHeight - otherHeight),
//                size = Size(barWidth, otherHeight),
//                cornerRadius = CornerRadius(20f, 20f) // Закруглено только сверху
//            )
//
//            // Женщины
//            drawRoundRect(
//                color = colorFemale,
//                topLeft = Offset(xOffset, yBase - maleHeight - femaleHeight),
//                size = Size(barWidth, femaleHeight),
//                cornerRadius = CornerRadius(20f, if (femaleHeight > 0) 20f else 0f) // Закругляем только верхний слой
//            )
//
//            // Мужчины
//            drawRoundRect(
//                color = colorMale,
//                topLeft = Offset(xOffset, yBase - maleHeight),
//                size = Size(barWidth, maleHeight),
//                cornerRadius = CornerRadius(20f, if (maleHeight > 0) 20f else 0f) // Закругляем только верхний слой
//            )
//
//            // Подписываем ось X (месяцы)
//            drawContext.canvas.nativeCanvas.drawText(
//                stat.date.month.name.take(3).uppercase(), // Первые 3 буквы месяца
//                xOffset + barWidth / 2,
//                maxBarHeight + 70f,
//                textPaint
//            )
//        }
//    }
}
