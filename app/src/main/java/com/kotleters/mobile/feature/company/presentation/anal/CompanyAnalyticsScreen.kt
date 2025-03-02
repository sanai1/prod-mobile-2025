package com.kotleters.mobile.feature.company.presentation.anal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth
import com.kotleters.mobile.feature.company.presentation.anal.components.AnimatedBarChart
import com.kotleters.mobile.feature.company.presentation.anal.states.AIState
import com.kotleters.mobile.feature.company.presentation.anal.states.AnalListState
import com.kotleters.mobile.feature.company.presentation.anal.states.CompanyAnalyticsScreenState
import java.time.LocalDate
import kotlin.math.atan

@Composable
fun CompanyAnalyticsScreen(
    viewModel: CompanyAnalScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
    ) {
        TopScreenHeader("Аналитика")
        LazyColumn {
            item {
                when ((state as CompanyAnalyticsScreenState.Content).aiState) {
                    is AIState.Content -> {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(secondaryGray)
                                .padding(16.dp)
                        ) {
                            Text(
                                "ИИ сгенерировал", fontSize = 16.sp, color = Color.White.copy(0.7f),
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(((state as CompanyAnalyticsScreenState.Content).aiState
                                    as AIState.Content).message, fontSize = 14.sp, fontWeight = FontWeight.Medium,
                                color = Color.White)
                        }
                    }

                    AIState.Error -> {

                    }

                    AIState.Loading -> {
                        ShimmerEffectCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                    }
                }
                when ((state as CompanyAnalyticsScreenState.Content).analListState) {
                    is AnalListState.Content -> {
                        AnimatedBarChart(
                            data = ((state
                                    as CompanyAnalyticsScreenState.Content).analListState
                                    as AnalListState.Content).list,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(16.dp)
                        )
                    }

                    AnalListState.Error -> {

                    }

                    AnalListState.Loading -> {
                        ShimmerEffectCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                    }
                }
            }
        }
    }

}