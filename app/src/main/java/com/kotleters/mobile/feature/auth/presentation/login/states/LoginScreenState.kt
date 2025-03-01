package com.kotleters.mobile.feature.auth.presentation.login.states

import com.kotleters.mobile.feature.auth.domain.UserAuth

sealed class LoginScreenState {

    data class Content(
        val auth: UserAuth
    ) : LoginScreenState()

    data object Loading : LoginScreenState()

    data object Error : LoginScreenState()

    data object Success : LoginScreenState()
}