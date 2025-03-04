package com.kotleters.mobile.feature.company.presentation.anal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.company.presentation.anal.CompanyAnalScreenViewModel
import com.kotleters.mobile.feature.company.presentation.anal.states.CompanyAnalyticsScreenState
import com.kotleters.mobile.feature.company.presentation.anal.states.LacunasState

@Composable
fun ImproveScreen(
    state: CompanyAnalyticsScreenState,
    viewModel: CompanyAnalScreenViewModel
) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState
    ) {
        when (state) {
            is CompanyAnalyticsScreenState.Content -> {
                when (state.lacunasState) {
                    is LacunasState.Content -> {
                        item {
                            Text(
                                "Создано при помощи ИИ",
                                fontSize = 16.sp,
                                color = Color.White.copy(0.7f),
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(16.dp)
                            )
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
                                    "Ваша  компания может заработать \n" +
                                            "дополнительно:",
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    "≈${state.lacunasState.lacunas.map { it.income }.sum()} ₽",
                                    color = Color(0xFF2F8A25),
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                "Лакуны, которые можно покрыть",
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        items(state.lacunasState.lacunas) {
                            LakunCard(it)
                        }
                    }

                    LacunasState.Error -> {
                        item {
                            ErrorState()
                        }
                    }

                    LacunasState.Loading -> {
                        item {
                            ShimmerEffectCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(500.dp)
                            )
                        }
                    }

                    LacunasState.OnBoard -> {
                        item {
                            OnBoardCard {
                                viewModel.startGenerate()
                            }
                        }
                    }
                }
            }

            is CompanyAnalyticsScreenState.DetailMessage -> {

            }
        }
    }
}