package com.kotleters.mobile.feature.company.data.repository

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.company.data.network.client.StatisticRetrofitClient
import com.kotleters.mobile.feature.company.data.network.mapper.StatisticModelMapper
import com.kotleters.mobile.feature.company.domain.entity.StatisticByDate
import com.kotleters.mobile.feature.company.domain.entity.StatisticByHour
import com.kotleters.mobile.feature.company.domain.entity.StatisticByMonth
import com.kotleters.mobile.feature.company.domain.repository.StatisticRepository

class StatisticRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : StatisticRepository {
    override suspend fun getStatisticByDate(): ResponseTemplate<List<StatisticByDate>> {
        try {
            val call = getStatisticByDateRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = StatisticModelMapper.toStatisticByDateList(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getStatisticByDateRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = StatisticModelMapper.toStatisticByDateList(call.body()!!)
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

    override suspend fun getStatisticByHour(): ResponseTemplate<List<StatisticByHour>> {
        try {
            val call = getStatisticByHourRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = StatisticModelMapper.toStatisticByHourList(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getStatisticByHourRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = StatisticModelMapper.toStatisticByHourList(callAgain.body()!!)
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

    override suspend fun getStatisticByMonth(): ResponseTemplate<List<StatisticByMonth>> {
        try {
            val call = getStatisticByMonthRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = StatisticModelMapper.toStatisticByMonthList(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getStatisticByMonthRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = StatisticModelMapper.toStatisticByMonthList(callAgain.body()!!)
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

    private fun getStatisticByDateRetrofit() =
        StatisticRetrofitClient.statisticRetrofitService.getStatisticByDate(
        token = getToken()
    ).execute()

    private fun getStatisticByHourRetrofit() =
        StatisticRetrofitClient.statisticRetrofitService.getStatisticByHour(
            token = getToken()
        ).execute()

    private fun getStatisticByMonthRetrofit() =
        StatisticRetrofitClient.statisticRetrofitService.getStatisticByMonth(
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
