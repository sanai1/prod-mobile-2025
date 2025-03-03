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
                discountList = offers.filter { OfferType.valueOf(it.type) == OfferType.DISCOUNT }.map {
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
                freeEveryList = offers.filter { OfferType.valueOf(it.type) == OfferType.STAMP }.map {
                    Company.FreeEvery(
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
                        freeEvery = it.free_every!!
                    )
                },
                bonus = offers.first { OfferType.valueOf(it.type) == OfferType.ACCUM }.let {
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
            )
        }
    }
}