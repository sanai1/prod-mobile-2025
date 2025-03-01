package com.kotleters.mobile.feature.client.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.ui.theme.secondaryGray

@Composable
fun CompanyCard(
    company: Company
) {

    Column(
        Modifier.padding(12.dp)
            .size(168.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(secondaryGray)
            .padding(16.dp)
    ) {
        Text(company.name, color = Color.White)
    }
}