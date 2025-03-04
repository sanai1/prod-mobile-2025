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
import com.kotleters.mobile.common.ui.components.CustomSlider
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.Slider
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.company.presentation.anal.components.AIShimmer
import com.kotleters.mobile.feature.company.presentation.anal.components.AnalSlider
import com.kotleters.mobile.feature.company.presentation.anal.components.AnimatedBarChart
import com.kotleters.mobile.feature.company.presentation.anal.components.ImproveScreen
import com.kotleters.mobile.feature.company.presentation.anal.components.StatsScreen
import com.kotleters.mobile.feature.company.presentation.anal.states.AIState
import com.kotleters.mobile.feature.company.presentation.anal.states.AnalListState
import com.kotleters.mobile.feature.company.presentation.anal.states.AnalState
import com.kotleters.mobile.feature.company.presentation.anal.states.CompanyAnalyticsScreenState
import com.kotleters.mobile.feature.company.presentation.anal.states.StatsState
import java.time.LocalDate
import kotlin.math.atan

val axisTypes = listOf(Pair("gender", "Пол"), Pair("age", "Возраст"))
val periods = listOf(
    Pair("month", "Месяц"),
    Pair("quarter", "Квартал"),
    Pair("year", "Год")
)
val analStates = listOf(
    Pair(AnalState.STATS, "Статистика"),
    Pair(AnalState.IMPROVE, "Улучшения")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyAnalyticsScreen(
    viewModel: CompanyAnalScreenViewModel
) {

    val state by viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        TopScreenHeader("Аналитика")
        CustomSlider(analStates, (state as CompanyAnalyticsScreenState.Content).currentState) {
            viewModel.changeState(it as AnalState)
        }
        when ((state as CompanyAnalyticsScreenState.Content).currentState) {
            AnalState.STATS -> {
                StatsScreen(state, viewModel)
            }

            AnalState.IMPROVE -> {
                ImproveScreen(state, viewModel)
            }
        }
    }

}

