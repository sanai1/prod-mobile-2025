package com.kotleters.mobile.feature.client.domain

import com.kotleters.mobile.common.ResponseTemplate
import com.kotleters.mobile.feature.client.data.network.model.ClientOffers

interface ClientRepository {
    suspend fun getAllOffers():ResponseTemplate<List<ClientOffers>>
}