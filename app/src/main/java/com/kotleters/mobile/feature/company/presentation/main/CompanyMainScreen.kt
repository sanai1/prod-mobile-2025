package com.kotleters.mobile.feature.company.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.feature.client.presentation.company.components.OfferCard
import com.kotleters.mobile.feature.company.presentation.main.states.CompanyMainScreenState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CompanyMainScreen(
    companyMainViewModel: CompanyMainViewModel,
    goToAdd: () -> Unit
) {

    val state by companyMainViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        TopScreenHeader("Компания")
        when(state){
            is CompanyMainScreenState.Content -> {
                LazyColumn {
                    item{
                        Row (
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text("Предложения", fontSize = 32.sp, fontWeight = FontWeight.Medium, color = lightGray)
                            Spacer(Modifier.weight(1f))
                            Text("+", fontSize = 32.sp, fontWeight = FontWeight.Medium, color = lightGray,
                                modifier = Modifier.noRippleClickable { goToAdd() })
                        }
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            (state as CompanyMainScreenState.Content).offers.forEach {
                                OfferCard(it) { }
                            }
                        }
                    }
                }
            }
            CompanyMainScreenState.Error ->{
                Text("Error", color = Color.White, modifier = Modifier.padding(16.dp))
            }
            CompanyMainScreenState.Loading -> {
                ShimmerEffectCard(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}