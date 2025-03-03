package com.kotleters.mobile.feature.client.presentation.offer.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QRShimmerEffect(
) {

    FlowRow(
        Modifier.size(300.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(25){
            ShimmerEffectCard(
                modifier = Modifier.padding(4.dp).size(50.dp),
                removePadding = true
            )
        }
    }
}