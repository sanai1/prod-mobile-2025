package com.kotleters.mobile.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopScreenHeader(
    title: String,
) {

    Column {
        Text(title, fontSize = 46.sp, color = Color.White, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(20.dp))
        HorizontalDivider(
            thickness = 0.3.dp,
            color = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        )
    }
}