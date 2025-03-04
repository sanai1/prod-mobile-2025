package com.kotleters.mobile.feature.company.presentation.pay.states

import com.kotleters.mobile.feature.company.domain.entity.ScanQr

sealed class CompanyPayScreenState {

    data object NotScanned : CompanyPayScreenState()

    data class Scanned(
        val scanQr: ScanQr,
    ) : CompanyPayScreenState()

    data object Error : CompanyPayScreenState()

    data object Loading : CompanyPayScreenState()
}