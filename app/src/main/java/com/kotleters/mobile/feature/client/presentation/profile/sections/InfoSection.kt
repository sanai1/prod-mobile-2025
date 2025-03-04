package com.kotleters.mobile.feature.client.presentation.profile.sections

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.Slider
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.extensions.noRippleClickable
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.client.domain.entity.TargetInfo
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreenViewModel
import com.kotleters.mobile.feature.client.presentation.profile.states.ClientProfileScreenState
import com.kotleters.mobile.feature.client.presentation.profile.states.InfoSectionState

@Composable
fun ClientProfileInfoSection(
    state: InfoSectionState,
    viewModel: ClientProfileScreenViewModel
) {

    val genders = listOf(
        Pair(TargetInfo.Gender.MALE, "Мужской"),
        Pair(TargetInfo.Gender.FEMALE, "Женский"),
        Pair(null, "Не указан")
    )

    when (state) {
        is InfoSectionState.Content -> {
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
                    chosen = state.gender
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
                if (state.age == null) {
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
                                viewModel.changeAge(state.age - 1)
                            }
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            state.age.toString(),
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = Bold
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            "+", color = Color.White, fontSize = 20.sp,
                            fontWeight = Bold,
                            modifier = Modifier.noRippleClickable {
                                viewModel.changeAge(state.age + 1)
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            WhiteButton(
                "Применить",
                state.age != null && state.gender != null
            ) {
                if (state.age != null && state.gender != null) {
                    viewModel.onSend()
                }
            }
        }

        InfoSectionState.Error -> {
            ErrorState()
        }

        InfoSectionState.Loading -> {
            ShimmerEffectCard(
                modifier = Modifier.fillMaxWidth().height(500.dp)
            )
        }
    }

}