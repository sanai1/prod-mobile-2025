package com.kotleters.mobile.feature.client.presentation.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.domain.entity.TargetInfo
import com.kotleters.mobile.feature.client.domain.repository.ClientRepository
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

    var isRefreshing = mutableStateOf(false)

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
            }

            ProfileSections.INFO -> {
                loadInfoState()
            }
        }
    }

    fun onRefresh() {
        isRefreshing.value = true
        when (currentState.value) {
            ProfileSections.LAKUNS -> {
                loadLakunsState()
            }

            ProfileSections.BONUSES -> {
                loadInfoState()
            }

            ProfileSections.INFO -> {
                loadInfoState()
            }
        }
    }

    private fun loadLakunsState() {
        viewModelScope.launch(Dispatchers.IO) {
            lakunaSectionState = LakunaSectionState.Loading
            updateState()
            val result = clientRepository.getLacuna()
            when(result){
                is ResponseTemplate.Error -> {
                    lakunaSectionState = LakunaSectionState.Error
                    updateState()
                }
                is ResponseTemplate.Success -> {
                    lakunaSectionState = LakunaSectionState.Content(
                        lacunas = result.data
                    )
                    updateState()
                }
            }
            isRefreshing.value = false
        }
    }

    private fun loadInfoState() {
        viewModelScope.launch(Dispatchers.IO) {
            if (infoSectionState !is InfoSectionState.Content) {
                infoSectionState = InfoSectionState.Loading
                updateState()
            }
            val result = clientRepository.getProfile()
            when (result) {
                is ResponseTemplate.Error -> {
                    infoSectionState = InfoSectionState.Error
                    bonusSectionState = BonusSectionState.Error
                    updateState()
                }

                is ResponseTemplate.Success -> {
                    infoSectionState = InfoSectionState.Content(
                        gender = result.data.targetInfo?.gender,
                        age = result.data.targetInfo?.age,
                        name = result.data.firstName
                    )
                    bonusSectionState = BonusSectionState.Content(
                        bonuses = result.data.bonus
                    )
                    updateState()
                }
            }
            isRefreshing.value = false
        }
    }

    fun changeAge(new: Int) {
        if (new in 1..100) {
            age.value = new
            infoSectionState = InfoSectionState.Content(
                gender = gender.value,
                age = age.value,
                name = (infoSectionState as InfoSectionState.Content).name
            )
            updateState()
        }
    }

    fun changeGender(new: TargetInfo.Gender?) {
        gender.value = new
        infoSectionState = InfoSectionState.Content(
            gender = gender.value,
            age = age.value,
            name = (infoSectionState as InfoSectionState.Content).name
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
                }

                is ResponseTemplate.Success -> {
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