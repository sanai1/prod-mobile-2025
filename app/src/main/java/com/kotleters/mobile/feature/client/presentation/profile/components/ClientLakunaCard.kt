package com.kotleters.mobile.feature.client.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.client.domain.entity.LacunaClient

@Composable
fun ClientLakunaCard(
    lacuna: LacunaClient
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
        Text(lacuna.category, fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        Text(
            lacuna.subcategory,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White.copy(0.7f)
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White.copy(0.3f),
            thickness = 0.5.dp
        )
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                    color = Color.White.copy(0.8f),
                    fontSize = 14.sp
                )
                ){
                    append(lacuna.text)
                }
            },
            maxLines = 4,
        )
    }

}