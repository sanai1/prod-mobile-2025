package com.kotleters.mobile.feature.client.domain

import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company

interface ClientRepository {
    suspend fun getAllOffers(): ResponseTemplate<List<Company>>
}