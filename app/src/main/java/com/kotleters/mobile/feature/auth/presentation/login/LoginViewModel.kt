package com.kotleters.mobile.feature.auth.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.auth.presentation.login.states.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: UserAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Loading)
    val state = _state.asStateFlow()

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private var isClient = mutableStateOf(false)

    private fun updateState(isCL: Boolean) {
        _state.update {
            LoginScreenState.Content(
                if (isCL) UserAuth.Client(
                    email = email.value,
                    password = password.value
                ) else UserAuth.Company(
                    email = email.value,
                    password = password.value
                )
            )
        }
    }
}