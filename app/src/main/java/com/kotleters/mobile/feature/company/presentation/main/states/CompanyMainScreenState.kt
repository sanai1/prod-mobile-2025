package com.kotleters.mobile.feature.company.presentation.main.states

import com.kotleters.mobile.common.domain.Company

sealed class CompanyMainScreenState {

    data object Error : CompanyMainScreenState()

    data object Loading : CompanyMainScreenState()

    data class Content(
        val offers: List<Company.Offer>
    ) : CompanyMainScreenState()
}