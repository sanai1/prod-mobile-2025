package com.kotleters.mobile.common.navigation

sealed class LoginState {

    data object Loading : LoginState()

    data object AuthCompany : LoginState()

    data object AuthClient : LoginState()

    data object NotAuth : LoginState()
}