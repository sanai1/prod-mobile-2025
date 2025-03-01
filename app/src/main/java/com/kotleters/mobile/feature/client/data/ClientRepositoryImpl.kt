package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.network.model.ClientOffers
import com.kotleters.mobile.common.network.model.ResponseTemplate
import com.kotleters.mobile.common.network.model.SecretStorage
import com.kotleters.mobile.feature.client.data.network.ClientRetrofitClient
import com.kotleters.mobile.feature.client.domain.ClientRepository

class ClientRepositoryImpl(
    private val context: Context
) : ClientRepository {

    override suspend fun getAllOffers(): ResponseTemplate<List<ClientOffers>> {
        return try {
            ResponseTemplate.Success(
                ClientRetrofitClient.clientRetrofitService.getAllOffers(getToken().toString())
            )
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun getToken() = SecretStorage.readToken(context)


}