package com.kotleters.mobile.feature.client.presentation.company

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.client.presentation.company.components.OfferCard
import com.kotleters.mobile.feature.client.presentation.main.ClientMainScreenViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CompanyDetailScreen(
    company: Company,
    onOfferClick: (Int) -> Unit,
    back: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .paint(
                    painterResource(R.drawable.fabric),
                    contentScale = ContentScale.Crop
                )
                .background(Color.Black.copy(alpha = 0.5f))
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            IconButton(back) {
                Icon(
                    Icons.AutoMirrored.Rounded.KeyboardArrowLeft, "",
                    tint = Color.White, modifier = Modifier.size(70.dp)
                )
            }
        }
        LazyColumn {
            item {
                Text(
                    company.name, fontSize = 46.sp, color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    company.discountList.forEach {
                        OfferCard(it) {
                            onOfferClick(company.discountList.indexOf(it))
                        }
                    }
                }
            }
        }
    }

}