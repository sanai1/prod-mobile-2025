package com.kotleters.mobile.feature.company.domain

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.data.network.model.ResponseTemplate

interface CompanyRepository {
    suspend fun createOffer(offer: Company.Offer): ResponseTemplate<Boolean>

    suspend fun getOffersByCompany(): ResponseTemplate<Company>
}
