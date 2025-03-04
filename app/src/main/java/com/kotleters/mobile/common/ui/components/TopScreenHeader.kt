package com.kotleters.mobile.common.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopScreenHeader(
    title: String,
    label: @Composable () -> Unit = {}
) {

    Column {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontSize = 36.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.weight(1f))
            label()
        }
        HorizontalDivider(
            thickness = 0.3.dp,
            color = Color.White.copy(alpha = 0.3f),
            modifier = Modifier.fillMaxWidth()
        )
    }
}