package com.kotleters.mobile.feature.company.domain

import com.kotleters.mobile.common.domain.Company

interface CompanyRepository {
    suspend fun createOffer(offer: Company.Offer)

    suspend fun getOffersByCompany(): Company
}
