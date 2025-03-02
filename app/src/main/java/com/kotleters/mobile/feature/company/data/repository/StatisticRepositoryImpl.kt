package com.kotleters.mobile.feature.company.data.repository

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.company.data.network.client.StatisticRetrofitClient
import com.kotleters.mobile.feature.company.data.network.model.StatisticModelMapper
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import com.kotleters.mobile.feature.company.domain.repository.StatisticRepository

class StatisticRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : StatisticRepository {
    override suspend fun getStatistic(): ResponseTemplate<List<Statistic>> {
        try {
            val call = getStatisticRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = StatisticModelMapper.toStatisticList(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getStatisticRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = StatisticModelMapper.toStatisticList(call.body()!!)
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

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private fun getStatisticRetrofit() = StatisticRetrofitClient.statisticRetrofitService.getAllStatistic(
        token = getToken()
    ).execute()

    private suspend fun updateToken() {
        val triple = SecretStorage.readPassAndEmail(context)
        userAuthRepository.auth(
            userAuth = UserAuth.Company(
                name = null,
                email = triple.first!!,
                password = triple.second!!
            )
        )
    }
}
