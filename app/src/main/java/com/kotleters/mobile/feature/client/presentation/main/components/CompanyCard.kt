package com.kotleters.mobile.feature.client.presentation.main.components

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.R
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.client.presentation.company.components.offersColors
import kotlin.math.absoluteValue


@Composable
fun CompanyCard(
    company: Company,
    onClick: () -> Unit
) {

    Column(
        Modifier
            .padding(16.dp)
            .size(168.dp)
            .clip(RoundedCornerShape(16.dp))
            .paint(painterResource(R.drawable.fabric), contentScale = ContentScale.Crop)
            .background(Color.Black.copy(alpha = 0.5f))
            .noRippleClickable { onClick() }
            .padding(16.dp)
    ) {
        Spacer(Modifier.weight(1f))
        Text(company.name, color = Color.White, fontSize = 24.sp,
            fontWeight = FontWeight.Medium)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${company.discountList.size} предложений", fontSize = 12.sp,
                color = lightGray)
            Spacer(Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, "",
                tint = lightGray)
        }
    }
}

fun getCompanyColor(uuid: String): Color {
    val index = uuid.hashCode().absoluteValue % offersColors.size
    return offersColors[index]
}