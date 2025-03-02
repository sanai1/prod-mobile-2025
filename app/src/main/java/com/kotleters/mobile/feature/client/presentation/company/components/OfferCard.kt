package com.kotleters.mobile.feature.client.presentation.company.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.ui.extensions.noRippleClickable

@Composable
fun OfferCard(
    offer: Company.Offer,
    onClick: () -> Unit
) {


    Column (
        Modifier
            .padding(16.dp)
            .size(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF6DAAAE))
            .noRippleClickable { onClick() }
            .padding(16.dp)
    ){
        Spacer(Modifier.weight(1f))
        Text("Скидка ${(offer.discount*10).toInt()}%", color = Color.White)
        Text(offer.title, fontSize = 16.sp, color = Color.White,
            fontWeight = FontWeight.Medium)
    }
}