package com.kotleters.mobile.feature.client.data

import android.content.Context
import android.util.Log
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.ClientRetrofitClient
import com.kotleters.mobile.feature.client.domain.ClientRepository
import retrofit2.HttpException

class ClientRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : ClientRepository {

    override suspend fun getAllOffers(): ResponseTemplate<List<Company>> {
        return try {

            val body = ClientRetrofitClient.clientRetrofitService.getAllOffers(getToken()).execute().body()
            ResponseTemplate.Success( CompanyMapper.map( body!!))

        } catch (h: HttpException) {
            if (h.code() == 403){
                val (pass, email) = getPassAndToken()
                val body = ClientRetrofitClient.clientRetrofitService.getAllOffers(getToken()).execute().body()

                userAuthRepository.auth(userAuth = UserAuth.Client(
                    firstName = null,
                    secondName = null,
                    email = email!!,
                    password = pass!!
                ))
                return ResponseTemplate.Success(CompanyMapper.map(body!!))
            }else {
                return ResponseTemplate.Error(message = "Ошибка HTTP ${h.code()}: ${h.message()}")
            }
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
            ResponseTemplate.Error(message = e.message.toString())

        }
    }

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private fun getPassAndToken() = SecretStorage.readPassAndEmail(context)
}