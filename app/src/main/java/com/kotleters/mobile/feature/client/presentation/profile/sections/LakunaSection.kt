package com.kotleters.mobile.feature.client.presentation.profile.sections

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotleters.mobile.common.ui.components.ShimmerEffectCard
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreenViewModel
import com.kotleters.mobile.feature.client.presentation.profile.components.ClientLakunaCard
import com.kotleters.mobile.feature.client.presentation.profile.states.LakunaSectionState

@Composable
fun ClientProfileLakunaSection(
    state: LakunaSectionState,
    viewModel: ClientProfileScreenViewModel
) {

    when (state) {
        LakunaSectionState.Error -> {

        }

        LakunaSectionState.Loading -> {
            ShimmerEffectCard(
                modifier = Modifier.fillMaxWidth()
                    .height(500.dp)
            )
        }

        is LakunaSectionState.Content -> {
            Text("Добавить лакуну", fontSize = 20.sp,
                fontWeight = FontWeight.Medium, color =
            Color(0xFF2F4ECB),
                modifier = Modifier.padding(16.dp)
            )
            repeat(10) {
                ClientLakunaCard()
            }
        }
    }

}