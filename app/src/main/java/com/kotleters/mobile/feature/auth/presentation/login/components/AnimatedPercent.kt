package com.kotleters.mobile.feature.auth.presentation.login.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPercentSymbol() {
    // Анимация увеличения и уменьшения
    val scaleAnim = rememberInfiniteTransition()
    val scale by scaleAnim.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row (
        Modifier.fillMaxWidth().padding(16.dp)
    ){
        Canvas(
            modifier = Modifier
                .size(300.dp)
        ) {
            val circleRadius = 40f * scale
            val lineWidth = 40f * scale
            val centerX = size.width / 2
            val centerY = size.height / 2

            // Верхний круг
            drawCircle(
                color = Color.White,
                radius = circleRadius,
                center = Offset(centerX - 80, centerY - 80)
            )

            // Нижний круг
            drawCircle(
                color = Color.White,
                radius = circleRadius,
                center = Offset(centerX + 80, centerY + 80)
            )

            // Наклоненная палочка
            drawLine(
                color = Color.White,
                start = Offset(centerX - 80, centerY + 80),
                end = Offset(centerX + 80, centerY - 80),
                strokeWidth = lineWidth,
                cap = StrokeCap.Round
            )
        }
    }
}
