package com.kotleters.mobile.feature.client.data

import android.content.Context
import com.kotleters.mobile.common.data.SecretStorage
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.client.data.network.client.ClientRetrofitClient
import com.kotleters.mobile.feature.client.data.network.mapper.ClientMapper
import com.kotleters.mobile.feature.client.data.network.model.LacunaCreateModel
import com.kotleters.mobile.feature.client.data.network.model.TargetInfoModel
import com.kotleters.mobile.feature.client.domain.entity.ClientProfile
import com.kotleters.mobile.feature.client.domain.entity.LacunaClient
import com.kotleters.mobile.feature.client.domain.entity.LacunaCreate
import com.kotleters.mobile.feature.client.domain.entity.TargetInfo
import com.kotleters.mobile.feature.client.domain.repository.ClientRepository

class ClientRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : ClientRepository {
    override suspend fun getProfile(): ResponseTemplate<ClientProfile> {
        try {
            val call = getProfileRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = ClientMapper.toClientProfile(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getProfileRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = ClientMapper.toClientProfile(callAgain.body()!!)
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

    override suspend fun getAllOffers(): ResponseTemplate<List<Company>> {
        try {
            val call = getOffers()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = CompanyMapper.map(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getOffers()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = CompanyMapper.map(callAgain.body()!!)
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

    override suspend fun updateTarget(targetInfo: TargetInfo): ResponseTemplate<Boolean> {
        try {
            val call = updateTargetRetrofit(targetInfo)
            if (call.code() == 200) {
                return ResponseTemplate.Success(data = true)
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = updateTargetRetrofit(targetInfo)
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(data = true)
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

    override suspend fun createLacuna(lacunaCreate: LacunaCreate): ResponseTemplate<Boolean> {
        try {
            val call = getLacunaCreateRetrofit(
                lacunaCreateModel = LacunaCreateModel(
                    averageSpent = lacunaCreate.amount,
                    categoryId = lacunaCreate.categoryId,
                    message = lacunaCreate.text
                )
            )
            if (call.code() == 200) {
                return ResponseTemplate.Success(data = true)
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getLacunaCreateRetrofit(
                    lacunaCreateModel = LacunaCreateModel(
                        averageSpent = lacunaCreate.amount,
                        categoryId = lacunaCreate.categoryId,
                        message = lacunaCreate.text
                    )
                )
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = true
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

    override suspend fun getLacuna(): ResponseTemplate<List<LacunaClient>> {
        try {
            val call = getLacunaRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = call.body()!!.map {
                        LacunaClient(
                            category = it.categoryName,
                            subcategory = it.subcategoryName,
                            text = it.message
                        )
                    }
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getLacunaRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = callAgain.body()!!.map {
                            LacunaClient(
                                category = it.categoryName,
                                subcategory = it.subcategoryName,
                                text = it.message
                            )
                        }
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

    private fun getProfileRetrofit() = ClientRetrofitClient.clientRetrofitService.getProfile(
        token = getToken()
    ).execute()

    private fun getOffers() = ClientRetrofitClient.clientRetrofitService.getAllOffers(
        token = getToken()
    ).execute()

    private fun getLacunaCreateRetrofit(lacunaCreateModel: LacunaCreateModel) = ClientRetrofitClient.clientRetrofitService.createLacuna(
        token = getToken(),
        lacunaCreateModel = lacunaCreateModel
    ).execute()

    private fun getLacunaRetrofit() = ClientRetrofitClient.clientRetrofitService.getLacuna(
        token = getToken()
    ).execute()

    private fun updateTargetRetrofit(targetInfo: TargetInfo) =
        ClientRetrofitClient.clientRetrofitService.updateTarget(
            token = getToken(),
            targetInfoModel = TargetInfoModel(
                age = targetInfo.age,
                gender = targetInfo.gender.name
            )
        ).execute()

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

    private suspend fun updateToken() {
        val triple = SecretStorage.readPassAndEmail(context)
        userAuthRepository.auth(
            userAuth = UserAuth.Company(
                email = triple.first!!,
                password = triple.second!!
            )
        )
    }
}