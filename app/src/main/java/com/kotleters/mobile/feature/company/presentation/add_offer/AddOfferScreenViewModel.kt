package com.kotleters.mobile.feature.company.presentation.add_offer

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotleters.mobile.common.category.domain.CategoryInfoRepository
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.feature.auth.presentation.register.parseDate
import com.kotleters.mobile.feature.company.domain.repository.CompanyRepository
import com.kotleters.mobile.feature.company.presentation.add_offer.states.AddOfferScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddOfferScreenViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AddOfferScreenState>(
        AddOfferScreenState.Content(
            title = "",
            description = "",
            discount = 0.1,
            startDate = "",
            endDate = ""
        )
    )
    val state = _state.asStateFlow()

    private var title = mutableStateOf("")
    private var description = mutableStateOf("")
    private var discount = mutableStateOf("0.1")
    private var startDate = mutableStateOf("")
    private var endDate = mutableStateOf("")

    var bonusFromPurchase = mutableStateOf(0.0)
    var bonusPaymentPercent = mutableStateOf(0.0)
    var offerPercent = mutableStateOf(0.0)

    var isBonus = mutableStateOf(false)

    fun changeTitle(new: String) {
        title.value = new
        updateData()
    }

    fun changeDescription(new: String) {
        description.value = new
        updateData()
    }

    fun changeDiscount(new: String) {
        discount.value = new
        updateData()
    }

    fun changeStartDate(new: String) {
        startDate.value = new
        updateData()
    }

    fun changeEndDate(new: String) {
        endDate.value = new
        updateData()
    }

    fun createOffer() {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                companyRepository.createOffer(
                    discount = Company.Discount(
                        title = title.value,
                        description = description.value,
                        discount = discount.value.toDouble(),
                        startDate = parseDate(startDate.value)!!,
                        endDate = parseDate(endDate.value)!!,
                        id = ""
                    ),
                    bonus = Company.Bonus(
                        id = "",
                        title = title.value,
                        description = description.value,
                        startDate = parseDate(startDate.value)!!,
                        endDate = parseDate(endDate.value)!!,
                        bonusFromPurchase = 0.2,
                        bonusPaymentPercent = 9.5
                    )
                )
            when (result) {
                is ResponseTemplate.Error -> {
                    Log.d("ERROR", result.message)
                    _state.update {
                        AddOfferScreenState.Error
                    }
                }

                is ResponseTemplate.Success -> {
                    _state.update {
                        AddOfferScreenState.Success
                    }
                }
            }
        }
    }


    private fun updateData() {
        _state.update {
            AddOfferScreenState.Content(
                title = title.value,
                description = description.value,
                discount = discount.value.toDouble(),
                startDate = startDate.value,
                endDate = endDate.value
            )
        }
    }
}