package com.kotleters.mobile.feature.client.data

import com.kotleters.mobile.common.network.model.ClientOffers
import com.kotleters.mobile.common.network.model.ResponseTemplate
import com.kotleters.mobile.feature.client.domain.ClientRepository

class ClientRepositoryImpl : ClientRepository {

    override suspend fun getAllOffers(): ResponseTemplate<List<ClientOffers>> {
        try {

        } catch (e: Exception) {

        }
        return TODO()
    }

}