package com.kotleters.mobile.feature.auth.presentation.register.company.components

import androidx.compose.runtime.Composable
import com.kotleters.mobile.common.ui.components.DefaultTextField
import com.kotleters.mobile.common.ui.components.WhiteButton
import com.kotleters.mobile.feature.auth.presentation.register.RegisterViewModel
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState

@Composable
fun RegStep4(
    viewModel: RegisterViewModel,
    state: RegisterScreenState
) {


    DefaultTextField("Введите email*", (state as RegisterScreenState.Content).registerStep4.email) {
        viewModel.changeCompanyEmail(it)
    }
    DefaultTextField(
        "Придумайте пароль*",
        (state as RegisterScreenState.Content).registerStep4.password,
        isPassword = true
    ) {
        viewModel.changeCompanyPassword(it)
    }

    WhiteButton(
        "Готово",
        state.registerStep4.password.isNotEmpty() &&
                state.registerStep4.email.isNotEmpty()
    ) {
        if (state.registerStep4.password.isNotEmpty() &&
            state.registerStep4.email.isNotEmpty()
        ) {
            viewModel.onRegister()
        }
    }

}