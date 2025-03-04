package com.kotleters.mobile.feature.client.presentation.profile

import android.graphics.Paint
import android.transition.Slide
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.CustomSlider
import com.kotleters.mobile.common.ui.components.Slider
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.lightGray
import com.kotleters.mobile.feature.client.presentation.profile.sections.ClientProfileBonusSection
import com.kotleters.mobile.feature.client.presentation.profile.sections.ClientProfileInfoSection
import com.kotleters.mobile.feature.client.presentation.profile.sections.ClientProfileLakunaSection
import com.kotleters.mobile.feature.client.presentation.profile.states.ClientProfileScreenState
import com.kotleters.mobile.feature.client.presentation.profile.states.InfoSectionState
import com.kotleters.mobile.feature.client.presentation.profile.states.ProfileSections
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientProfileScreen(
    back: () -> Unit,
    addLakuna: () -> Unit,
    viewModel: ClientProfileScreenViewModel
) {

    val state by viewModel.state.collectAsState()

    val sections = listOf(
        Pair(ProfileSections.LAKUNS, "Лакуна"),
        Pair(ProfileSections.BONUSES, "Бонусы"),
        Pair(ProfileSections.INFO, "О себе")
    )

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding(),
        isRefreshing = viewModel.isRefreshing.value,
        onRefresh = {
            viewModel.onRefresh()
        },
    ) {
        Column {
            TopScreenHeader("Профиль", label = {
                Icon(Icons.AutoMirrored.Rounded.ExitToApp, "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(35.dp)
                        .noRippleClickable {
                            viewModel.onLogOut()
                            back()
                        })
            })
            LazyColumn {
                item {
                    when (state) {
                        is ClientProfileScreenState.Content -> {
                            if ((state as ClientProfileScreenState.Content).infoSectionState is InfoSectionState.Content) {
                                Text(
                                    "Привет,\n${((state as ClientProfileScreenState.Content).infoSectionState as InfoSectionState.Content).name}!",
                                    fontSize = 32.sp,
                                    fontWeight = Medium,
                                    color = lightGray,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                    CustomSlider(
                        sections,
                        (state as ClientProfileScreenState.Content).currentState
                    ) {
                        viewModel.changeSection(it as ProfileSections)
                    }
                    when (state) {
                        is ClientProfileScreenState.Content -> {
                            when ((state as ClientProfileScreenState.Content).currentState) {
                                ProfileSections.LAKUNS -> {
                                    ClientProfileLakunaSection(
                                        addLakuna,
                                        (state as ClientProfileScreenState.Content).lakunaSectionState,
                                        viewModel,
                                    )
                                }

                                ProfileSections.BONUSES -> {
                                    ClientProfileBonusSection((state as ClientProfileScreenState.Content).bonusSectionState)
                                }

                                ProfileSections.INFO -> {
                                    ClientProfileInfoSection(
                                        (state as ClientProfileScreenState.Content).infoSectionState,
                                        viewModel = viewModel
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