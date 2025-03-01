package com.kotleters.mobile.feature.client.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.feature.client.presentation.main.components.CompanyCard
import com.kotleters.mobile.feature.client.presentation.main.states.ClientMainScreenState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ClientMainScreen(
    viewModel: ClientMainScreenViewModel
) {

    val state by viewModel.state.collectAsState()
    val refreshState = rememberPullToRefreshState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        TopScreenHeader("Главная")
        when (state) {
            is ClientMainScreenState.Content -> {
                LazyColumn {
                    item {
                        FlowRow {
                            (state as ClientMainScreenState.Content).companies.forEach {
                                CompanyCard(company = it)
                            }
                        }
                    }
                }
            }

            is ClientMainScreenState.Error -> {
                Text((state as ClientMainScreenState.Error).message, color = Color.White)
            }

            ClientMainScreenState.Loading -> {
                ShimmerEffectCard(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}