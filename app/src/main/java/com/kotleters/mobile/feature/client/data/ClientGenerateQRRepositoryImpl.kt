package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.data.SecretStorage
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.client.ClientGenerateQRClient
import com.kotleters.mobile.feature.client.domain.repository.ClientGenerateQRRepository

class ClientGenerateQRRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : ClientGenerateQRRepository {
    override suspend fun clientGenerateQRRepository(
        offerId: String,
        spendBonus: Boolean
    ): ResponseTemplate<String> {
        try {
            val call = getPayload(offerId, spendBonus.toString())
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = call.body()!!.payload
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getPayload(offerId, spendBonus.toString())
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = callAgain.body()!!.payload
                    )
                } else {
                    ResponseTemplate.Error(message = callAgain.message())
                }
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun getPayload(offerId: String, spendBonus: String) =
        ClientGenerateQRClient.clientGenerateQRService.getPayload(
            token = getToken(),
            offerId = offerId,
            spendBonus = spendBonus,
        ).execute()

    private suspend fun updateToken() {
        val triple = SecretStorage.readPassAndEmail(context)
        userAuthRepository.auth(
            userAuth = UserAuth.Client(
                firstName = null,
                secondName = null,
                email = triple.first!!,
                password = triple.second!!
            )
        )
    }

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"
}