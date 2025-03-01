package com.kotleters.mobile.feature.auth.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: UserAuthRepository
) : ViewModel() {


    private val _state = MutableStateFlow<RegisterScreenState>(RegisterScreenState.Loading)
    val state = _state.asStateFlow()

    private var userName = mutableStateOf("")
    private var userSecondName = mutableStateOf("")
    private var userEmail = mutableStateOf("")
    private var userPassword = mutableStateOf("")

    private var companyName = mutableStateOf("")
    private var companyEmail = mutableStateOf("")
    private var companyPassword = mutableStateOf("")

    private var isClient = mutableStateOf(false)

    fun changeUserName(name: String){
        userName.value = name
    }

    fun setupRegister(isCL: Boolean) {
        isClient.value = isCL
        updateState(isClient.value)
    }

    private fun updateState(isClient: Boolean) {
        viewModelScope.launch {
            if (isClient){
                _state.update {
                    RegisterScreenState.Content(
                        userAuth = UserAuth.Client(
                            firstName = userName.value,
                            secondName = userSecondName.value,
                            email = userEmail.value,
                            password = userPassword.value
                        )
                    )
                }
            }else{
                _state.update {
                    RegisterScreenState.Content(
                        userAuth = UserAuth.Company(
                            name = companyName.value,
                            email = companyEmail.value,
                            password = companyPassword.value
                        )
                    )
                }
            }
        }
    }

}