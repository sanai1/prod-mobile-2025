package com.kotleters.mobile.common.domain

import com.kotleters.mobile.common.data.network.model.ClientOfferModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CompanyMapper {
    fun map(companyOffers: List<ClientOfferModel>): List<Company> {
        return companyOffers.groupBy { it.company_id }.map { (companyId, offers) ->
            Company(
                id = companyId,
                name = offers.first().company_name,
                offers = offers.map { offer ->
                    Company.Offer(
                        id = offer.id,
                        type = OfferType.valueOf(offer.type),
                        title = offer.title,
                        description = offer.description,
                        startDate = LocalDateTime.parse(
                            offer.start_date,
                            DateTimeFormatter.ISO_DATE_TIME
                        ),
                        endDate = LocalDateTime.parse(
                            offer.end_date,
                            DateTimeFormatter.ISO_DATE_TIME
                        ),
                        discount = offer.discount,
                        freeEvery = offer.free_every,
                        bonusFromPurchase = offer.bonus_from_purchase,
                        bonusPaymentPercent = offer.bonus_payment_percent,
                    )
                }
            )
        }
    }
}