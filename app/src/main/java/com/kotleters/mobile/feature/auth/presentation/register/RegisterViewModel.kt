package com.kotleters.mobile.feature.auth.presentation.register

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.photo.data.PhotoRepositoryImpl
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: UserAuthRepository,
    private val photoRepository: PhotoRepository
) : ViewModel() {


    private val _state = MutableStateFlow<RegisterScreenState>(RegisterScreenState.Loading)
    val state = _state.asStateFlow()

    var userName = mutableStateOf("")
    var userSecondName = mutableStateOf("")
    var userEmail = mutableStateOf("")
    var userPassword = mutableStateOf("")

    var companyName = mutableStateOf("")
    var companyEmail = mutableStateOf("")
    var companyPassword = mutableStateOf("")

    var photoUri = mutableStateOf<Uri>(Uri.parse(""))

    var isError = mutableStateOf(false)

    private var isClient = mutableStateOf(false)

    fun changePhoto(new: Uri){
        photoUri.value = new
    }

    fun changeCompanyEmail(name: String) {
        companyEmail.value = name
        updateState(isClient.value)
    }

    fun changeCompanyPassword(name: String) {
        companyPassword.value = name
        updateState(isClient.value)
    }

    fun changeCompanyName(name: String) {
        companyName.value = name
        updateState(isClient.value)
    }

    fun changeUserSecondName(name: String) {
        userSecondName.value = name
        updateState(isClient.value)
    }

    fun changeUserPassword(name: String) {
        userPassword.value = name
        updateState(isClient.value)
    }

    fun changeUserEmail(name: String) {
        userEmail.value = name
        updateState(isClient.value)
    }

    fun changeUserName(name: String) {
        userName.value = name
        updateState(isClient.value)
    }

    fun setupRegister(isCL: Boolean) {
        isClient.value = isCL
        updateState(isClient.value)
    }

    fun onRegister() {
        viewModelScope.launch(Dispatchers.IO) {
            val authState = (_state.asStateFlow().value as RegisterScreenState.Content).userAuth
            _state.update {
                RegisterScreenState.Loading
            }
            val result = authRepository.register(authState)
            val photoResult = photoRepository.addCompanyPhoto(photoUri.value)
            when (result) {
                is ResponseTemplate.Error -> {
                    _state.update {
                        RegisterScreenState.Content(
                            userAuth = authState,
                            isError = true
                        )
                    }
                }

                is ResponseTemplate.Success -> {
                    _state.update {
                        RegisterScreenState.Success
                    }
                }
            }
        }
    }

    private fun updateState(isClient: Boolean) {
        viewModelScope.launch {
            if (isClient) {
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
            } else {
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