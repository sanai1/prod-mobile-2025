package com.kotleters.mobile.feature.company.presentation.pay.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.company.domain.entity.ScanQr

@Composable
fun SuccessPay(
    scanQr: ScanQr,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(secondaryGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("\uD83C\uDF89", fontSize = 100.sp)
            Spacer(Modifier.height(25.dp))
            Text(
                "Акция успешно применена!", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = Color.White
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 25.dp)
                    .fillMaxWidth(),
                thickness = 0.3.dp,
                color = Color.White.copy(alpha = 0.5f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    "Клиент", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "${scanQr.firstName} ${scanQr.lastName}",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    "Акция", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "${scanQr.offerTitle}",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    "Скидка", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "${(scanQr.discount * 10).toInt()}%",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(Modifier.height(16.dp))
            WhiteButton("Продолжить!", true) {
                onClick()
            }
        }
    }
}