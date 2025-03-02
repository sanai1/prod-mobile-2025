package com.kotleters.mobile.feature.company.presentation.anal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.company.presentation.anal.components.AnimatedBarChart

@Composable
fun CompanyAnalyticsScreen() {

    Column(
        Modifier.fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
    ){
        TopScreenHeader("Аналитика")
        LazyColumn {
            item{
                val data = listOf(
                    "Мужской" to 50f,
                    "Женский" to 100f,
                    "Не указан" to 75f,
                    "Мужской" to 120f,
                    "Женский" to 90f
                )

                AnimatedBarChart(
                    data = data,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp)
                )
            }
        }
    }

}