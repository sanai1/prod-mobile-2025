package com.kotleters.mobile.feature.client.domain

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.network.model.ResponseTemplate

interface ClientRepository {
    suspend fun getAllOffers(): ResponseTemplate<List<Company>>
}