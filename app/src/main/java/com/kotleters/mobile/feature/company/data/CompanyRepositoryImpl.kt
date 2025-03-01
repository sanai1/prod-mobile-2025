package com.kotleters.mobile.feature.company.data

import android.content.Context
import android.util.Log
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.common.domain.Payload
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.company.data.network.CompanyRetrofitClient
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
import com.kotleters.mobile.feature.company.data.network.model.ScanQrMapper
import com.kotleters.mobile.feature.company.domain.CompanyRepository
import com.kotleters.mobile.feature.company.domain.ScanQr

class CompanyRepositoryImpl(
    private val context: Context,
    private val userAuthRepository: UserAuthRepository
) : CompanyRepository {
    override suspend fun createOffer(offer: Company.Offer): ResponseTemplate<Boolean> {
        val offerForCreate = OfferCompanyCreateModel(
            title = offer.title,
            description = offer.description,
            discount = offer.discount.toDouble(),
            startDate = offer.startDate.toString(),
            endDate = offer.endDate.toString()
        )
        try {
            val call = create(offerForCreate)
            if (call.code() == 200) {
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

    override suspend fun getOffersByCompany(): ResponseTemplate<Company> {
        try {
            Log.d("TOKEN", "${SecretStorage.readToken(context)}")
            val call = getOffers()
            Log.d("CODE", call.code().toString())
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
            Log.d("SUCCESS", e.message.toString())
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun scanQr(payload: Payload): ResponseTemplate<ScanQr> {
        try {
            val call = scanQrRetrofit(payload)

            if (call.code() == 200) {
                return ResponseTemplate.Success(
                    data = ScanQrMapper.toScanQr(call.body()!!)
                )
            } else if (call.code() == 401) {
                updateToken()
                val callAgain = scanQrRetrofit(payload)
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

    private fun create(offer: OfferCompanyCreateModel) = CompanyRetrofitClient.companyRetrofitService.createOffer(
        token = getToken(),
        offer = offer
    ).execute()
    
    private fun getOffers() = CompanyRetrofitClient.companyRetrofitService.getOffersByCompany(
        token = getToken()
    ).execute()

    private fun scanQrRetrofit(payload: Payload) = CompanyRetrofitClient.companyRetrofitService.scanQr(
        token = getToken(),
        payload = payload
    ).execute()

    private fun getToken() = "Bearer ${SecretStorage.readToken(context)}"

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
