package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.ClientRetrofitClient
import com.kotleters.mobile.feature.client.domain.ClientRepository
import retrofit2.HttpException
import javax.inject.Inject

class ClientRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : ClientRepository {

    override suspend fun getAllOffers(): ResponseTemplate<List<Company>> {
        return try {
            ResponseTemplate.Success( CompanyMapper.map( ClientRetrofitClient.clientRetrofitService.getAllOffers(getToken()!!)))

        } catch (h: HttpException) {
            if (h.code() == 403){
                val (pass, email) = getPassAndToken()

                userAuthRepository.auth(userAuth = UserAuth.Client(
                    firstName = null,
                    secondName = null,
                    email = email!!,
                    password = pass!!
                ))
                return ResponseTemplate.Success(CompanyMapper.map(ClientRetrofitClient.clientRetrofitService.getAllOffers(getToken()!!)))
            }else {
                return ResponseTemplate.Error(message = "Ошибка HTTP ${h.code()}: ${h.message()}")
            }
        } catch (e: Exception) {
            ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun getToken() = SecretStorage.readToken(context)

    private fun getPassAndToken() = SecretStorage.readPassAndEmail(context)

}