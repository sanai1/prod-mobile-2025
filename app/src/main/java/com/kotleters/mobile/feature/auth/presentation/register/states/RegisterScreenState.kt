package com.kotleters.mobile.feature.auth.presentation.register.states

import com.kotleters.mobile.feature.auth.domain.UserAuth

sealed class RegisterScreenState {

    data class Content(
        val userAuth: UserAuth,
        val isError: Boolean = false,
    ) : RegisterScreenState()

    data object Loading : RegisterScreenState()

    data object Success : RegisterScreenState()
}