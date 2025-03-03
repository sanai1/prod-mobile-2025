package com.kotleters.mobile.feature.company.presentation.anal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.secondaryGray

@Composable
fun AIShimmer() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(175.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(secondaryGray)
            .padding(16.dp)
    ) {
        Text(
            "ИИ исследует", fontSize = 16.sp, color = Color.White.copy(0.7f),
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(10.dp))

        ShimmerEffectCard(
            modifier = Modifier.fillMaxSize(),
            removePadding = true
        )
    }
}