package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel

object OfferMapper {
    fun toOfferCompanyCreateModel(offer: Company.Offer): OfferCompanyCreateModel {
        return OfferCompanyCreateModel(
            type = offer.type.name,
            title = offer.title,
            description = offer.description,
            start_date = offer.startDate.toString(),
            end_date = offer.endDate.toString(),
            discount = offer.discount,
            free_every = offer.freeEvery,
            bonus_from_purchase = offer.bonusFromPurchase,
            bonus_payment_percent = offer.bonusPaymentPercent
        )
    }
}