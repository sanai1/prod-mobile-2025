package com.kotleters.mobile.feature.company.presentation.pay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.client.data.network.model.PayloadClient
import com.kotleters.mobile.feature.company.data.network.model.PayloadCompany
import com.kotleters.mobile.feature.company.domain.repository.CompanyRepository
import com.kotleters.mobile.feature.company.presentation.pay.states.CompanyPayScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.cos

@HiltViewModel
class CompanyPayScreenViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CompanyPayScreenState>(CompanyPayScreenState.NotScanned)
    val state = _state.asStateFlow()

    fun backToScan() {
        _state.update {
            CompanyPayScreenState.NotScanned
        }
    }

    fun scanQR(payload: String, cost: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                CompanyPayScreenState.Loading
            }
            val result = companyRepository.scanQr(
                PayloadCompany(
                cost = cost,
                    payload = payload
                )
            )

            when (result) {
                is ResponseTemplate.Error -> {
                    _state.update {
                        CompanyPayScreenState.Error
                    }
                }

                is ResponseTemplate.Success -> {
                    _state.update {
                        CompanyPayScreenState.Scanned(
                            scanQr = result.data
                        )
                    }
                }
            }
        }
    }
}