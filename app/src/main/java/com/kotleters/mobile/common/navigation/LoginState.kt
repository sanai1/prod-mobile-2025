package com.kotleters.mobile.common.navigation

sealed class LoginState {

    data object Loading : LoginState()

    data object Auth : LoginState()

    data object NotAuth : LoginState()
}