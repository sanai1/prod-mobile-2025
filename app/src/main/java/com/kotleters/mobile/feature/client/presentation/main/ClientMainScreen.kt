package com.kotleters.mobile.feature.client.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.components.states.EmptyState
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.feature.client.presentation.main.components.CompanyCard
import com.kotleters.mobile.feature.client.presentation.main.states.ClientMainScreenState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ClientMainScreen(
    viewModel: ClientMainScreenViewModel,
    goToCompany: (Int) -> Unit
) {

    val state by viewModel.state.collectAsState()
    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = state is ClientMainScreenState.Loading,
        onRefresh = { viewModel.fetchCompanies() },
    ) {
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
                            Text(
                                "Предложения от\n" +
                                        "компаний", fontSize = 26.sp,
                                fontWeight = FontWeight.Medium, color = lightGray,
                                modifier = Modifier.padding(16.dp)
                            )
                            if ((state as ClientMainScreenState.Content).companies.isEmpty()){
                                EmptyState()
                            }else{
                                FlowRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    (state as ClientMainScreenState.Content).companies.forEach {
                                        CompanyCard(company = it, onClick = {
                                            goToCompany(
                                                (state as ClientMainScreenState.Content).companies.indexOf(
                                                    it
                                                )
                                            )
                                        })
                                    }
                                }
                            }
                        }
                    }
                }

                is ClientMainScreenState.Error -> {
                    ErrorState()
                }

                ClientMainScreenState.Loading -> {
                    ShimmerEffectCard(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}