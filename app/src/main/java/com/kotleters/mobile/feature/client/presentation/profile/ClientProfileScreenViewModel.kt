package com.kotleters.mobile.feature.client.presentation.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.domain.ClientRepository
import com.kotleters.mobile.feature.client.domain.TargetInfo
import com.kotleters.mobile.feature.client.presentation.profile.states.BonusSectionState
import com.kotleters.mobile.feature.client.presentation.profile.states.ClientProfileScreenState
import com.kotleters.mobile.feature.client.presentation.profile.states.InfoSectionState
import com.kotleters.mobile.feature.client.presentation.profile.states.LakunaSectionState
import com.kotleters.mobile.feature.client.presentation.profile.states.ProfileSections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        MutableStateFlow<ClientProfileScreenState>(
            ClientProfileScreenState.Content(
                currentState = ProfileSections.INFO,
                lakunaSectionState = LakunaSectionState.Loading,
                bonusSectionState = BonusSectionState.Loading,
                infoSectionState = InfoSectionState.Loading
            )
        )
    val state = _state.asStateFlow()

    private var currentState = mutableStateOf<ProfileSections>(ProfileSections.INFO)

    private var bonusSectionState: BonusSectionState = BonusSectionState.Loading
    private var infoSectionState: InfoSectionState = InfoSectionState.Loading
    private var lakunaSectionState: LakunaSectionState = LakunaSectionState.Loading

    private var age = mutableStateOf<Int?>(null)
    private var gender = mutableStateOf<TargetInfo.Gender?>(null)

    init {
        changeSection(ProfileSections.INFO)
    }

    fun changeSection(profileSection: ProfileSections) {
        currentState.value = profileSection
        updateState()
        when (profileSection) {
            ProfileSections.LAKUNS -> {
                loadLakunsState()
            }

            ProfileSections.BONUSES -> {
                loadBonusState()
            }

            ProfileSections.INFO -> {
                loadInfoState()
            }
        }
    }

    private fun loadBonusState() {
        viewModelScope.launch(Dispatchers.IO) {
            bonusSectionState = BonusSectionState.Loading
            updateState()
            delay(1000)
            bonusSectionState = BonusSectionState.Content(
                bonuses = 543.56
            )
            updateState()
        }
    }

    private fun loadLakunsState() {
        viewModelScope.launch(Dispatchers.IO) {
            lakunaSectionState = LakunaSectionState.Loading
            updateState()
            delay(1000)
            lakunaSectionState = LakunaSectionState.Loading
            updateState()
        }
    }

    private fun loadInfoState() {
        viewModelScope.launch(Dispatchers.IO) {
            infoSectionState = InfoSectionState.Loading
            updateState()
            delay(1000)
            infoSectionState = InfoSectionState.Content(
                gender = TargetInfo.Gender.MALE,
                age = 17
            )
            updateState()
        }
    }

    fun changeAge(new: Int) {
        if (new in 1..100) {
            age.value = new
            infoSectionState = InfoSectionState.Content(
                gender = gender.value,
                age = age.value
            )
            updateState()
        }
    }

    fun changeGender(new: TargetInfo.Gender?) {
        gender.value = new
        infoSectionState = InfoSectionState.Content(
            gender = gender.value,
            age = age.value
        )
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
                currentState = currentState.value,
                lakunaSectionState = lakunaSectionState,
                bonusSectionState = bonusSectionState,
                infoSectionState = infoSectionState
            )
        }
    }
}