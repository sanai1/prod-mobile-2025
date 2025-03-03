package com.kotleters.mobile.feature.company.presentation.main.states

import com.kotleters.mobile.common.domain.Company

sealed class CompanyMainScreenState {

    data class Content(
        val offersState: OffersState,
        val infoState: InfoState,
    ) : CompanyMainScreenState()
}

sealed class InfoState {

    data object Error : InfoState()

    data object Loading : InfoState()

    data class Content(
        val image: String?,
        val name: String
    ) : InfoState()
}

sealed class OffersState {

    data object Error : OffersState()

    data object Loading : OffersState()

    data class Content(
        val offers: List<Company.Discount>,
    ) : OffersState()
}