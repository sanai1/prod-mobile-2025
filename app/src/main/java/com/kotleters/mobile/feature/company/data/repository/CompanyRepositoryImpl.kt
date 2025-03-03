package com.kotleters.mobile.feature.company.data.repository

import android.content.Context
import com.kotleters.mobile.common.data.SecretStorage
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.common.domain.Lacuna
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.company.data.network.client.CompanyRetrofitClient
import com.kotleters.mobile.feature.company.data.network.mapper.CompanyProfileMapper
import com.kotleters.mobile.feature.company.data.network.mapper.OfferMapper
import com.kotleters.mobile.feature.company.data.network.mapper.ScanQrMapper
import com.kotleters.mobile.feature.company.data.network.mapper.StatisticModelMapper
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
import com.kotleters.mobile.feature.company.data.network.model.PayloadCompany
import com.kotleters.mobile.feature.company.domain.entity.CompanyProfile
import com.kotleters.mobile.feature.company.domain.entity.ScanQr
import com.kotleters.mobile.feature.company.domain.entity.Statistic
import com.kotleters.mobile.feature.company.domain.repository.CompanyRepository

class CompanyRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : CompanyRepository {
    override suspend fun getProfile(): ResponseTemplate<CompanyProfile> {
        try {
            val call = getProfileRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = CompanyProfileMapper.toCompanyProfile(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getProfileRetrofit()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = CompanyProfileMapper.toCompanyProfile(callAgain.body()!!)
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
    
    override suspend fun createOffer(
        discount: Company.Discount?,
        bonus: Company.Bonus?,
    ): ResponseTemplate<Boolean> {
        val offerForCreate = OfferMapper.toOfferCompanyCreateModel(
            discount = discount,
            bonus = bonus,
        )
        if (offerForCreate == null) {
            return ResponseTemplate.Error(message = "all data is null")
        }
        try {
            val call = create(offerForCreate)
            if (call.code() == 201) {
                return ResponseTemplate.Success(data = true)
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = create(offerForCreate)
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

    override suspend fun getOffersByCompany(): ResponseTemplate<Company?> {
        try {
            val call = getOffers()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = CompanyMapper.map(call.body()!!)[0]
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getOffers()
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = CompanyMapper.map(callAgain.body()!!)[0]
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

    override suspend fun scanQr(payload: PayloadCompany): ResponseTemplate<ScanQr> {
        try {
            val call = scanQrRetrofit(payload.copy(cost = payload.cost / 6.0))
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = ScanQrMapper.toScanQr(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = scanQrRetrofit(payload.copy(cost = payload.cost / 6.0))
                return if (callAgain.code() == 200) {
                    ResponseTemplate.Success(
                        data = ScanQrMapper.toScanQr(call.body()!!)
                    )
                } else {
                    ResponseTemplate.Error(message = call.message())
                }
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun getStatistic(): ResponseTemplate<Statistic> {
        try {
            val call = getStatisticByMonthRetrofit()
            if (call.code() == 200) {
                StatisticModelMapper.toStatistic(call.body()!!).also {
                    return if (it == null) {
                        ResponseTemplate.Error(message = "no data available for analytics")
                    } else {
                        ResponseTemplate.Success(
                            data = it
                        )
                    }
                }
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = getStatisticByMonthRetrofit()
                return if (callAgain.code() == 200) {
                    StatisticModelMapper.toStatistic(callAgain.body()!!).let {
                        if (it == null) {
                            ResponseTemplate.Error(message = "no data available for analytics")
                        } else {
                            ResponseTemplate.Success(
                                data = it
                            )
                        }
                    }
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

    override suspend fun getLacunas(): ResponseTemplate<List<Lacuna>> {
        try {
            val call = getLacunaRetrofit()
            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = call.body()!!.map {
                        Lacuna(
                            averageSpent = it.averageSpent,
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
                            Lacuna(
                                averageSpent = it.averageSpent,
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

    private fun getProfileRetrofit() = CompanyRetrofitClient.companyRetrofitService.getProfile(
        token = getToken()
    ).execute()

    private fun create(offer: OfferCompanyCreateModel) = CompanyRetrofitClient.companyRetrofitService.createOffer(
        token = getToken(),
        offer = offer
    ).execute()
    
    private fun getOffers() = CompanyRetrofitClient.companyRetrofitService.getOffersByCompany(
        token = getToken()
    ).execute()

    private fun scanQrRetrofit(payload: PayloadCompany) =
        CompanyRetrofitClient.companyRetrofitService.scanQr(
        token = getToken(),
        payload = payload
    ).execute()

    private fun getStatisticByMonthRetrofit() = CompanyRetrofitClient.companyRetrofitService.getStatisticByMonth(
        token = getToken()
    ).execute()

    private fun getLacunaRetrofit() = CompanyRetrofitClient.companyRetrofitService.getLacuna(
        token = getToken(),
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
