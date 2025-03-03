package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.SecretStorage
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.client.ClientGenerateQRClient
import com.kotleters.mobile.feature.client.domain.ClientGenerateQRRepository

class ClientGenerateQRRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : ClientGenerateQRRepository {
    override suspend fun clientGenerateQRRepository(offerId: String): ResponseTemplate<String> {
        return try {
            val call =
                ClientGenerateQRClient.clientGenerateQRService.getPayload(offerId, getToken())
                    .execute()
            if (call.code() == 401) {
                val (email, pass) = getPassAndEmail()

                userAuthRepository.auth(
                    userAuth = UserAuth.Client(
                        firstName = null,
                        secondName = null,
                        email = email!!,
                        password = pass!!
                    )
                )
                val call =
                    ClientGenerateQRClient.clientGenerateQRService.getPayload(
                        token = getToken(),
                        offerId = offerId
                    )
                        .execute()
                return if (call.body() != null) {
                    ResponseTemplate.Success(call.body()!!.payload)
                } else {
                    ResponseTemplate.Error("hui")
                }
            } else {
                if (call.body() != null) {
                    ResponseTemplate.Success(call.body()!!.payload)
                } else {
                    ResponseTemplate.Error("hui")
                }
            }

        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }


    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private fun getPassAndEmail() = SecretStorage.readPassAndEmail(context)
}