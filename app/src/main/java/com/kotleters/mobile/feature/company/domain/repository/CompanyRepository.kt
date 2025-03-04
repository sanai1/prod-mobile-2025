package com.kotleters.mobile.feature.company.domain.repository

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.company.data.network.model.PayloadCompany
import com.kotleters.mobile.feature.company.domain.entity.CompanyProfile
import com.kotleters.mobile.feature.company.domain.entity.ScanQr
import com.kotleters.mobile.feature.company.domain.entity.Statistic

interface CompanyRepository {
    suspend fun getProfile(): ResponseTemplate<CompanyProfile>

    suspend fun createOffer(
        discount: Company.Discount? = null,
        bonus: Company.Bonus? = null
    ): ResponseTemplate<Boolean>

    suspend fun getOffersByCompany(): ResponseTemplate<Company?>

    suspend fun scanQr(payload: PayloadCompany): ResponseTemplate<ScanQr>

    suspend fun getStatistic(): ResponseTemplate<Statistic>

    suspend fun getLacunas(): ResponseTemplate<List<Lacuna>>
}
