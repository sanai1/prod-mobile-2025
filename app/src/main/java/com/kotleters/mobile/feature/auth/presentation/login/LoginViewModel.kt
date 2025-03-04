package com.kotleters.mobile.feature.auth.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.auth.presentation.login.states.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: UserAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Loading)
    val state = _state.asStateFlow()

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    var isError = mutableStateOf(false)

    private var isClient = mutableStateOf(false)

    fun onLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            val authState = (_state.asStateFlow().value as LoginScreenState.Content).auth
            _state.update {
                LoginScreenState.Loading
            }
            val result = authRepository.auth(authState)
            when (result) {
                is ResponseTemplate.Error -> {
                    _state.update {
                        LoginScreenState.Content(
                            authState,
                            isError = true
                        )
                    }
                }

                is ResponseTemplate.Success -> {

                    _state.update {
                        LoginScreenState.Success
                    }
                }
            }
        }
    }

    fun updateEmail(new: String) {
        email.value = new
        updateState(isClient.value)
    }

    fun updatePassword(new: String) {
        password.value = new
        updateState(isClient.value)
    }

    fun setupLogin(isCL: Boolean) {
        isClient.value = isCL
        updateState(isClient.value)
    }

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