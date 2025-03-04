package com.kotleters.mobile.feature.client.presentation.company.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.kotleters.mobile.common.ui.theme.lightGray
import java.util.UUID
import kotlin.math.absoluteValue

val offersColors = listOf(
    Color(0xFF6D7EAE),
    Color(0xFF6DAAAE),
    Color(0xFF8C6DAE),
    Color(0xFF8CAE6D),
    Color(0xFFAE6D7E),
    Color(0xFFAE8C6D)
)

@Composable
fun OfferCard(
    offer: Company.Discount,
    onClick: () -> Unit
) {

    Column(
        Modifier
            .padding(16.dp)
            .size(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(getColorForUUID(offer.id))
            .noRippleClickable { onClick() }
            .padding(16.dp)
    ) {
        Text(
            offer.title, fontSize = 22.sp, color = lightGray,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.weight(1f))
        Row {
            Spacer(Modifier.weight(1f))
            Text(
                "${(offer.discount).toInt()}%", color = Color.White, fontSize = 46.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

fun getColorForUUID(uuid: String): Color {
    val index = uuid.hashCode().absoluteValue % offersColors.size
    return offersColors[index]
}