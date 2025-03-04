package com.kotleters.mobile.feature.company.presentation.main

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.kotleters.mobile.R
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.feature.client.presentation.company.components.OfferCard
import com.kotleters.mobile.feature.company.presentation.main.states.CompanyMainScreenState
import com.kotleters.mobile.feature.company.presentation.main.states.InfoState
import com.kotleters.mobile.feature.company.presentation.main.states.OffersState

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CompanyMainScreen(
    back: () -> Unit,
    companyMainViewModel: CompanyMainViewModel,
    goToAdd: () -> Unit
) {

    val state by companyMainViewModel.state.collectAsState()


    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding(),
        isRefreshing = (state as CompanyMainScreenState.Content).infoState is InfoState.Loading
                && (state as CompanyMainScreenState.Content).offersState is OffersState.Loading,
        onRefresh = {
            companyMainViewModel.onRefresh()
        },
    ) {
        Column {
            TopScreenHeader("Компания", label = {
                Icon(Icons.AutoMirrored.Rounded.ExitToApp, "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                        .noRippleClickable {
                            companyMainViewModel.onLogOut()
                            back()
                        })
            })
            when (state) {
                is CompanyMainScreenState.Content -> {
                    LazyColumn {
                        item {
                            when ((state as CompanyMainScreenState.Content).infoState) {
                                is InfoState.Content -> {
                                    Box() {
                                        Image(
                                            painter = painterResource(R.drawable.fabric),
                                            contentDescription = "Loaded Image",
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .fillMaxWidth()
                                                .height(220.dp)
                                                .clip(RoundedCornerShape(16.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                        Box(
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .fillMaxWidth()
                                                .height(220.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                                .background(
                                                    Brush.verticalGradient(
                                                        listOf(
                                                            Color.Black.copy(alpha = 0f),
                                                            Color.Black.copy(alpha = 1f)
                                                        )
                                                    )
                                                )
                                                .padding(16.dp),
                                            contentAlignment = Alignment.BottomStart
                                        ) {
                                            Text(
                                                ((state as CompanyMainScreenState.Content).infoState as InfoState.Content).name,
                                                fontSize = 32.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = Color.White
                                            )
                                        }
                                    }
                                }

                                InfoState.Error -> {

                                }

                                InfoState.Loading -> {
                                    ShimmerEffectCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(220.dp)
                                    )
                                }
                            }

                            when ((state as CompanyMainScreenState.Content).offersState) {
                                is OffersState.Content -> {
                                    Row(
                                        modifier = Modifier
                                            .padding(20.dp)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            "Предложения",
                                            fontSize = 26.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = lightGray
                                        )
                                        Spacer(Modifier.weight(1f))
                                        Text("+",
                                            fontSize = 32.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = lightGray,
                                            modifier = Modifier.noRippleClickable { goToAdd() })
                                    }

                                    FlowRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        ((state as CompanyMainScreenState.Content).offersState as OffersState.Content).offers.forEach {
                                            OfferCard(it) { }
                                        }
                                    }
                                }

                                OffersState.Error -> {
                                    ErrorState()
                                }

                                OffersState.Loading -> {
                                    ShimmerEffectCard(
                                        modifier = Modifier.height(300.dp)
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}