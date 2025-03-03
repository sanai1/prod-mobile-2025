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

@Composable
fun LakunCard() {

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
            "Хуевое качество котлет для \n" +
                    "бургеров в фастфуде",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Может принести: ", fontSize = 16.sp, color = Color.White)
            Text(
                "≈154 754 ₽",
                fontSize = 16.sp,
                color = Color(0xFF2F8A25),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(
                    color = Color.White.copy(0.8f),
                    fontSize = 14.sp
                )){
                    append("Многие клиенты считают, что в крупных сетях\n" +
                            "фаст-фуда качество котлет полное ")
                }
                withStyle(SpanStyle(
                    color = Color(0xFF2F4ECB),
                    fontSize = 14.sp
                )){
                    append("еще...")
                }
            },
            modifier = Modifier.noRippleClickable {

            }
        )
    }

}