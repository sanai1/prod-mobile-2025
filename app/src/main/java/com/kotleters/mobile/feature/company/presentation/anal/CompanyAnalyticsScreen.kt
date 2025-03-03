package com.kotleters.mobile.feature.company.presentation.anal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.Slider
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.company.presentation.anal.components.AnalSlider
import com.kotleters.mobile.feature.company.presentation.anal.components.AnimatedBarChart
import com.kotleters.mobile.feature.company.presentation.anal.states.AIState
import com.kotleters.mobile.feature.company.presentation.anal.states.AnalListState
import com.kotleters.mobile.feature.company.presentation.anal.states.CompanyAnalyticsScreenState
import java.time.LocalDate
import kotlin.math.atan

val axisTypes = listOf(Pair("gender", "Пол"), Pair("age", "Возраст"))
val periods = listOf(Pair("month", "Месяц"),
    Pair("quarter", "Квартал"),
    Pair("year", "Год"))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyAnalyticsScreen(
    viewModel: CompanyAnalScreenViewModel
) {

    val state by viewModel.state.collectAsState()
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var yAxisType by remember { mutableStateOf("gender") } // "gender" или "age"
    var period by remember { mutableStateOf("month") } // "month", "quarter", "year"

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        TopScreenHeader("Аналитика")
        LazyColumn {
            item {

                when ((state as CompanyAnalyticsScreenState.Content).aiState) {
                    is AIState.Content -> {
                        AIMessageBox(((state as CompanyAnalyticsScreenState.Content).aiState
                                as AIState.Content).message, more = {
                            isExpanded = true
                        })
                    }

                    AIState.Error -> {

                    }

                    AIState.Loading -> {
                        ShimmerEffectCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }
            item {
                AnalSlider(axisTypes, yAxisType) {
                    yAxisType = it
                }
                AnalSlider(periods, period) {
                    period = it
                }
                AnimatedBarChart(
                    data = testData,
                    yAxisType = yAxisType,
                    period = period,
                    modifier = Modifier.fillMaxWidth().height(500.dp).padding(24.dp)
                )
            }
//            item{
//                when ((state as CompanyAnalyticsScreenState.Content).analListState) {
//                    is AnalListState.Content -> {
////                        AnimatedBarChart(
////                            data = ((state
////                                    as CompanyAnalyticsScreenState.Content).analListState
////                                    as AnalListState.Content).list,
////                            modifier = Modifier
////                                .padding(16.dp)
////                                .fillMaxWidth()
////                                .height(300.dp)
////                                .padding(16.dp)
////                        )
//                    }
//
//                    AnalListState.Error -> {
//
//                    }
//
//                    AnalListState.Loading -> {
//                        ShimmerEffectCard(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(300.dp)
//                        )
//                    }
//                }
//            }
        }
    }

    if (isExpanded){
        ModalBottomSheet(
            {
                isExpanded = false
            },
            containerColor = backgroundColor,
            shape = RoundedCornerShape(16.dp)
        ){
            LazyColumn(
                Modifier.padding(16.dp)
            ) {
                item {
                    Text(
                        "ИИ сгенерировал", fontSize = 22.sp, color = Color.White.copy(0.7f),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = ((state as CompanyAnalyticsScreenState.Content).aiState
                                as AIState.Content).message,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                    )
                }
            }
        }
    }

}

@Composable
fun AIMessageBox(message: String, more: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val textStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White
    )

    val maxLines = if (expanded) Int.MAX_VALUE else 3 // Ограничение строк

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(secondaryGray)
            .padding(16.dp)
    ) {
        Text(
            "ИИ сгенерировал", fontSize = 16.sp, color = Color.White.copy(0.7f),
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(10.dp))

        Text(
            text = message,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )

        if (!expanded) {
            Spacer(Modifier.height(8.dp))
            Text(
                "Еще...",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2F4ECB),
                modifier = Modifier.noRippleClickable { more() }
            )
        }
    }
}
