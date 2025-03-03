package com.kotleters.mobile.feature.auth.presentation.register.company.components

import androidx.compose.runtime.Composable
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState

@Composable
fun RegStep3(
    viewModel: RegisterViewModel,
    state: RegisterScreenState
) {


    DefaultTextField("Название*", (state as RegisterScreenState.Content).registerStep3.title) {
        viewModel.changeOfferTitle(it)
    }
    DefaultTextField(
        "Описание*",
        (state as RegisterScreenState.Content).registerStep3.description
    ) {
        viewModel.changeOfferDescription(it)
    }
    WhiteButton(
        "Продолжить",
        state.registerStep3.title.isNotEmpty()
    ) {
        viewModel.nextStep()
    }

}