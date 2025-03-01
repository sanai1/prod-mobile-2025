package com.kotleters.mobile.common.navigation

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.domain.UserLogIn
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val authRepository: UserAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.checkLogIn()
            _state.update {
               when(result){
                   UserLogIn.CLIENT ->  LoginState.AuthClient
                   UserLogIn.COMPANY -> LoginState.AuthCompany
                   else -> LoginState.NotAuth
               }
//                LoginState.NotAuth
            }
        }
    }
}