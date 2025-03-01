package com.kotleters.mobile.feature.client.domain

import com.kotleters.mobile.common.ResponseTemplate
import com.kotleters.mobile.common.network.model.ClientOffers

interface ClientRepository {
    suspend fun getAllOffers():ResponseTemplate<List<ClientOffers>>
}