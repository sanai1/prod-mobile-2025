package com.kotleters.mobile.feature.client.presentation.profile.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotleters.mobile.feature.client.presentation.profile.ClientProfileScreenViewModel
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

        }
    }

}