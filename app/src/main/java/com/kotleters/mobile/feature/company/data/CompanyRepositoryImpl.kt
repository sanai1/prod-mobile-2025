package com.kotleters.mobile.feature.company.data

import android.content.Context
import com.kotleters.mobile.common.data.network.model.ResponseTemplate
import com.kotleters.mobile.common.data.network.model.SecretStorage
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.CompanyMapper
import com.kotleters.mobile.feature.auth.domain.UserAuth
import com.kotleters.mobile.feature.auth.domain.UserAuthRepository
import com.kotleters.mobile.feature.company.data.network.CompanyRetrofitClient
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
import com.kotleters.mobile.feature.company.domain.CompanyRepository
import retrofit2.HttpException

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
            create(offerForCreate)
            return ResponseTemplate.Success(data = true)
        } catch (http: HttpException) {
            if (http.code() == 403) {
                updateToken()
                create(offerForCreate).also {
                    return if (it.code() == 200) {
                        ResponseTemplate.Success(data = true)
                    } else {
                        ResponseTemplate.Error(message = it.message())
                    }
                }
            } else { throw Exception() }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    override suspend fun getOffersByCompany(): ResponseTemplate<Company> {
        try {
            return getOffers().let {
                if (it.body() != null) {
                    ResponseTemplate.Success(
                        data = CompanyMapper.map(it.body()!!)[0]
                    )
                } else {
                    ResponseTemplate.Error(message = it.message())
                }
            }
        } catch (http: HttpException) {
            if (http.code() == 403) {
                updateToken()
                return getOffers().let { 
                    if (it.body() != null) {
                        ResponseTemplate.Success(
                            data = CompanyMapper.map(it.body()!!)[0]
                        )
                    } else {
                        ResponseTemplate.Error(message = it.message())
                    }
                }
            } else { throw Exception() }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }

    private fun create(offer: OfferCompanyCreateModel) = CompanyRetrofitClient.companyRetrofitService.createOffer(
        token = "Bearer ${SecretStorage.readToken(context)}",
        offer = offer
    ).execute()
    
    private fun getOffers() = CompanyRetrofitClient.companyRetrofitService.getOffersByCompany(
        token = "Bearer ${SecretStorage.readToken(context)}",
        limit = 1000,
        offset = 0,
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
