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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kotleters.mobile.common.ui.components.Slider
import com.kotleters.mobile.common.ui.components.TopScreenHeader
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.backgroundColor
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.client.domain.TargetInfo
import kotlinx.coroutines.launch

@Composable
fun ClientProfileScreen(
    viewModel: ClientProfileScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val genders = listOf(
        Pair(TargetInfo.Gender.MALE, "Мужской"),
        Pair(TargetInfo.Gender.FEMALE, "Женский"),
        Pair(null, "Не указан")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .systemBarsPadding()
    ) {
        TopScreenHeader("Профиль", label = {
            Icon(Icons.AutoMirrored.Rounded.ExitToApp, "",
                tint = Color.White,
                modifier = Modifier
                    .size(70.dp)
                    .noRippleClickable {
                        viewModel.onLogOut()
                    })
        })
        LazyColumn {
            item {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Пол",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Slider(
                        genders,
                        chosen = viewModel.gender.value
                    ) {
                        viewModel.changeGender(it)
                    }
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Возраст",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    if (viewModel.age.value == null) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(secondaryGray)
                                .noRippleClickable {
                                    viewModel.changeAge(10)
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Указать возраст", color = Color.White, fontSize = 20.sp,
                                fontWeight = Bold
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(secondaryGray)
                                .padding(16.dp)
                        ) {
                            Text(
                                "-", color = Color.White, fontSize = 20.sp,
                                fontWeight = Bold,
                                modifier = Modifier.noRippleClickable {
                                    viewModel.changeAge(viewModel.age.value!! - 1)
                                }
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                viewModel.age.value.toString(),
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = Bold
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                "+", color = Color.White, fontSize = 20.sp,
                                fontWeight = Bold,
                                modifier = Modifier.noRippleClickable {
                                    viewModel.changeAge(viewModel.age.value!! + 1)
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))
                WhiteButton(
                    "Применить",
                    viewModel.age.value != null && viewModel.gender.value != null
                ) {
                    if (viewModel.age.value != null && viewModel.gender.value != null) {
                        viewModel.onSend()
                    }
                }

            }
        }
    }
}