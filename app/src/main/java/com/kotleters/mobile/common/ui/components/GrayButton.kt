package com.kotleters.mobile.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.secondaryGray

@Composable
fun GrayButton(
    text: String,
    isEnabled: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(secondaryGray.copy(alpha = if (isEnabled) 1f else 0.5f))
                .noRippleClickable {
                    onClick()
                }
                .padding(vertical = 16.dp, horizontal = 25.dp),
            contentAlignment = Alignment.Center
        ) {

            Text(text, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Medium)
        }
    }

}