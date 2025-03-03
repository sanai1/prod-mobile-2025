package com.kotleters.mobile.feature.company.domain.repository

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Payload
import com.kotleters.mobile.feature.company.domain.entity.ScanQr
import com.kotleters.mobile.feature.company.domain.entity.Statistic

interface CompanyRepository {
    suspend fun createOffer(
        discount: Company.Discount? = null,
        bonus: Company.Bonus? = null
    ): ResponseTemplate<Boolean>

    suspend fun getOffersByCompany(): ResponseTemplate<Company?>

    suspend fun scanQr(payload: Payload): ResponseTemplate<ScanQr>

    suspend fun getStatistic(): ResponseTemplate<Statistic>
}
