package com.kotleters.mobile.feature.company.presentation.main

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
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
    private val companyRepository: CompanyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CompanyMainScreenState>(CompanyMainScreenState.Loading)
    val state = _state.asStateFlow()

    private val offers = mutableStateListOf<Company.Offer>()

    init {
        fetchOffers()
    }

    fun fetchOffers() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("HUI", "f")
            _state.update {
                CompanyMainScreenState.Loading
            }
            val result = companyRepository.getOffersByCompany()
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("RESULT", result.toString())
                    _state.update {
                        CompanyMainScreenState.Error
                    }
                }

                is ResponseTemplate.Success -> {
                    offers.clear()
                    offers.addAll(result.data.offers)
                    updateState()
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