package com.kotleters.mobile.feature.company.presentation.main

import android.util.Log
import androidx.camera.core.processing.SurfaceProcessorNode.In
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.auth.presentation.onboard.AuthViewModel
import com.kotleters.mobile.feature.company.domain.repository.CompanyRepository
import com.kotleters.mobile.feature.company.presentation.main.states.CompanyMainScreenState
import com.kotleters.mobile.feature.company.presentation.main.states.InfoState
import com.kotleters.mobile.feature.company.presentation.main.states.OffersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyMainViewModel @Inject constructor(
    private val companyRepository: CompanyRepository,
    private val photoRepository: PhotoRepository,
    private val authRepository: UserAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CompanyMainScreenState>(
        CompanyMainScreenState.Content(
            offersState = OffersState.Loading,
            infoState = InfoState.Loading
        )
    )
    val state = _state.asStateFlow()

    private val offers = mutableStateListOf<Company.Offer>()
    private val companyId = mutableStateOf("")

    private val companyName = mutableStateOf("")

    private var offerState: OffersState = OffersState.Loading
    private var infoState: InfoState = InfoState.Loading

    init {
        fetchData()
    }

    fun onLogOut() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.logOut()
        }
    }

    private fun fetchPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            infoState = InfoState.Loading
            updateState()
            val result = photoRepository.getCompanyPhoto(companyId.value)
            when (result) {
                is ResponseTemplate.Error -> {
                    infoState = InfoState.Error
                    updateState()
                }

                is ResponseTemplate.Success -> {
                    infoState = InfoState.Content(
                        image = result.data,
                        name = companyName.value
                    )
                    updateState()
                }
            }
        }
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            offerState = OffersState.Loading
            infoState = InfoState.Loading
            updateState()
            val result = companyRepository.getOffersByCompany()
            when (result) {
                is ResponseTemplate.Error -> {
                    offerState = OffersState.Error
                    infoState = InfoState.Error
                    updateState()
                }

                is ResponseTemplate.Success -> {
                    offers.clear()
                    result.data?.let { offers.addAll(it.offers) }
                    companyId.value = result.data?.id ?: ""
                    companyName.value = result.data?.name ?: ""
                    offerState = OffersState.Content(
                        offers = result.data?.offers ?: listOf()
                    )
                    updateState()
                    fetchPhoto()
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            CompanyMainScreenState.Content(
                offersState = offerState,
                infoState = infoState
            )
        }
    }
}