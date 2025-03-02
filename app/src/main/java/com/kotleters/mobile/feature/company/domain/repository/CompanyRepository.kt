package com.kotleters.mobile.feature.company.domain.repository

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Payload
import com.kotleters.mobile.feature.company.domain.entity.ScanQr

interface CompanyRepository {
    suspend fun createOffer(offer: Company.Offer): ResponseTemplate<Boolean>

    suspend fun getOffersByCompany(): ResponseTemplate<Company?>

    suspend fun scanQr(payload: Payload): ResponseTemplate<ScanQr>
}
