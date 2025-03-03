package com.kotleters.mobile.feature.company.presentation.anal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.company.presentation.anal.model.LacunaUI

@Composable
fun LakunCard(
    lacunaUI: LacunaUI
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(secondaryGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            lacunaUI.headline,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Может принести: ", fontSize = 16.sp, color = Color.White)
            Text(
                "≈${lacunaUI.income} ₽",
                fontSize = 16.sp,
                color = Color(0xFF2F8A25),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.White.copy(0.8f),
                        fontSize = 14.sp,
                    )
                ) {
                    append(lacunaUI.description)
                }
            },
            modifier = Modifier.noRippleClickable {

            },
            maxLines = 3
        )
    }

}