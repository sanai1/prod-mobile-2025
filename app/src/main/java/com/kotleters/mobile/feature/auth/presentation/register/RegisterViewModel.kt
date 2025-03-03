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
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterStep1
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterStep2
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterStep3
import com.kotleters.mobile.feature.auth.presentation.register.states.RegisterStep4
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
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

    private var currentRegisterStep = mutableStateOf(0)

    private var registerStep1 = RegisterStep1(
        photo = null,
        title = ""
    )
    private var registerStep2 = RegisterStep2(
        category = "",
        underCategory = ""
    )
    private var registerStep3 = RegisterStep3(
        title = "",
        percent = 0,
        description = "",
        startDate = LocalDateTime.now(),
        endDate = LocalDateTime.now()
    )
    private var registerStep4 = RegisterStep4(
        email = "",
        password = "",
        passwordAgain = ""
    )

    var photoUri = mutableStateOf<Uri>(Uri.parse(""))

    var isError = mutableStateOf(false)

    private var isClient = mutableStateOf(false)

    fun changePhoto(new: Uri) {
        photoUri.value = new
    }

    fun nextStep() {
        currentRegisterStep.value++
        updateState(isClient.value)
    }

    fun changeCategory(new: String) {
        registerStep2 = registerStep2.copy(category = new)
        updateState(isClient.value)
    }

    fun changeUnderCategory(new: String) {
        registerStep2 = registerStep2.copy(underCategory = new)
        updateState(isClient.value)
    }

    fun changeOfferTitle(new: String) {
        registerStep3 = registerStep3.copy(title = new)
        updateState(isClient.value)
    }

    fun changeOfferPercent(new: Int) {
        registerStep3 = registerStep3.copy(percent = new)
        updateState(isClient.value)
    }

    fun changeOfferDescription(new: String) {
        registerStep3 = registerStep3.copy(description = new)
        updateState(isClient.value)
    }

    fun changeOfferStartDate(new: String) {
        registerStep3 = registerStep3.copy(startDate = LocalDateTime.now())
        updateState(isClient.value)
    }

    fun changeOfferEndDate(new: String) {
        registerStep3 = registerStep3.copy(endDate = LocalDateTime.now())
        updateState(isClient.value)
    }

    fun changeCompanyEmail(name: String) {
        registerStep4 = registerStep4.copy(email = name)
        updateState(isClient.value)
    }

    fun changeCompanyPassword(name: String) {
        registerStep4 = registerStep4.copy(password = name)
        updateState(isClient.value)
    }

    fun changeCompanyPasswordAgain(name: String) {
        registerStep4 = registerStep4.copy(passwordAgain = name)
        updateState(isClient.value)
    }

    fun changeCompanyName(name: String) {
        registerStep1 = registerStep1.copy(title = name)
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
                    isError.value = true
                    updateState(isClient.value)
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
//                _state.update {
//                    RegisterScreenState.Content(
//
//                    )
//                }
            } else {
                _state.update {
                    RegisterScreenState.Content(
                        currentRegisterStep = currentRegisterStep.value,
                        registerStep1 = registerStep1,
                        registerStep2 = registerStep2,
                        registerStep3 = registerStep3,
                        registerStep4 = registerStep4,
                        isError = isError.value,
                        userAuth = UserAuth.Company(
                            name = "",
                            email = "",
                            password = ""
                        )
                    )
                }
            }
        }
    }

}