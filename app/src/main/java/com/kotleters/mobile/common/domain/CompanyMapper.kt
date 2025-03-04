package com.kotleters.mobile.common.domain

import android.util.Log
import com.kotleters.mobile.common.data.network.model.ClientOfferModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CompanyMapper {
    fun map(companyOffers: List<ClientOfferModel>): List<Company> {
        return companyOffers.groupBy { it.company_id }.map { (companyId, offers) ->
            Log.d("OFFERS", offers.toString())
            Company(
                id = companyId,
                name = offers.first().company_name,
                photoUrl = "https://prod-team-10-avk8n3cp.final.prodcontest.ru/api/company/${companyId}/image",
                discountList = offers.filter { OfferType.valueOf(it.type) == OfferType.DISCOUNT }
                    .map {
                        Company.Discount(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            startDate = LocalDateTime.parse(
                                it.start_date,
                                DateTimeFormatter.ISO_DATE_TIME
                            ),
                            endDate = LocalDateTime.parse(
                                it.end_date,
                                DateTimeFormatter.ISO_DATE_TIME
                            ),
                            discount = it.discount!!
                        )
                    },
                bonus = offers.firstOrNull { OfferType.valueOf(it.type) == OfferType.ACCUM }.let {
                    if (it == null) {
                        null
                    } else {
                        Company.Bonus(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            startDate = LocalDateTime.parse(
                                it.start_date,
                                DateTimeFormatter.ISO_DATE_TIME
                            ),
                            endDate = LocalDateTime.parse(
                                it.end_date,
                                DateTimeFormatter.ISO_DATE_TIME
                            ),
                            bonusFromPurchase = it.bonus_from_purchase!!,
                            bonusPaymentPercent = it.bonus_payment_percent!!
                        )
                    }
                }
            )
        }
    }
}