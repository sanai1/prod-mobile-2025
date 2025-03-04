package com.kotleters.mobile.feature.client.presentation.profile.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.common.ui.components.states.ErrorState
import com.kotleters.mobile.common.ui.theme.secondaryGray
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreenViewModel
import com.kotleters.mobile.feature.client.presentation.profile.states.BonusSectionState

@Composable
fun ClientProfileBonusSection(
    state: BonusSectionState,
) {

    when (state) {
        is BonusSectionState.Content -> {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(secondaryGray)
                        .padding(16.dp)
                ) {
                    Text(
                        "Бонусы",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            state.bonuses.toString(), fontSize = 64.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "баллoв", fontSize = 24.sp,
                            color = Color.White.copy(alpha = 0.7f),
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }

        BonusSectionState.Error -> {
            ErrorState()
        }

        BonusSectionState.Loading -> {
            ShimmerEffectCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )
        }
    }
}