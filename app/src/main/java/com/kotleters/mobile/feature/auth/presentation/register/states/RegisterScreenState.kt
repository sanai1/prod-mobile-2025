package com.kotleters.mobile.feature.auth.presentation.register.states

import com.kotleters.mobile.feature.auth.domain.UserAuth

sealed class RegisterScreenState {

    data class Content(
        val userAuth: UserAuth
    ) : RegisterScreenState()

    data object Error : RegisterScreenState()

    data object Loading : RegisterScreenState()
}