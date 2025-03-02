package com.kotleters.mobile.feature.client.presentation.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.domain.ClientRepository
import com.kotleters.mobile.feature.client.domain.TargetInfo
import com.kotleters.mobile.feature.client.presentation.profile.states.ClientProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProfileScreenViewModel @Inject constructor(
    private val clientRepository: ClientRepository,
    private val authRepository: UserAuthRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow<ClientProfileScreenState>(ClientProfileScreenState.Content())
    val state = _state.asStateFlow()

    var age = mutableStateOf<Int?>(null)
    var gender = mutableStateOf<TargetInfo.Gender?>(null)

    fun changeAge(new: Int) {
        if (new in 1..100) {
            age.value = new
            updateState()
        }
    }

    fun changeGender(new: TargetInfo.Gender?) {
        gender.value = new
        updateState()
    }

    fun onSend() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = gender.value?.let {
                age.value?.let { it1 ->
                    TargetInfo(
                        age = it1,
                        gender = it
                    )
                }
            }?.let {
                clientRepository.updateTarget(
                    targetInfo = it
                )
            }
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("PENIS", result.message)
                    age.value = 62
                }

                is ResponseTemplate.Success -> {
                    age.value = 52
                }

                null -> {

                }
            }
        }
    }

    fun onLogOut() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logOut()
        }
    }


    private fun updateState() {
        _state.update {
            ClientProfileScreenState.Content(
                age = age.value,
                gender = gender.value
            )
        }
    }
}