package com.kotleters.mobile.feature.company.presentation.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.photo.domain.PhotoRepository
import com.kotleters.mobile.feature.company.domain.CompanyRepository
import com.kotleters.mobile.feature.company.presentation.main.states.CompanyMainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyMainViewModel @Inject constructor(
    private val companyRepository: CompanyRepository,
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CompanyMainScreenState>(CompanyMainScreenState.Loading)
    val state = _state.asStateFlow()

    private val offers = mutableStateListOf<Company.Offer>()
    private val companyId = mutableStateOf("")

    var photo = mutableStateOf(ByteArray(8))

    init {
        fetchOffers()
    }

    fun fetchPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = photoRepository.getCompanyPhoto(companyId.value)
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("ERROR", result.message)
                }

                is ResponseTemplate.Success -> {
                    photo.value = result.data
                }
            }
        }
    }

    fun fetchOffers() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                CompanyMainScreenState.Loading
            }
            val result = companyRepository.getOffersByCompany()
            when (result) {
                is ResponseTemplate.Error -> {
                    _state.update {
                        CompanyMainScreenState.Error
                    }
                }

                is ResponseTemplate.Success -> {
                    offers.clear()
                    result.data?.let { offers.addAll(it.offers) }
                    companyId.value = result.data?.id ?: ""
                    updateState()
                    Log.d("ID", companyId.value)
                    fetchPhoto()
                }
            }
        }
    }

    private fun updateState() {
        _state.update {
            CompanyMainScreenState.Content(
                offers = offers
            )
        }
    }
}