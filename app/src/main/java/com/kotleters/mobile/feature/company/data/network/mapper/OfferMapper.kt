package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.OfferType
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel

object OfferMapper {
    fun toOfferCompanyCreateModel(
        discount: Company.Discount? = null,
        freeEvery: Company.FreeEvery? = null,
        bonus: Company.Bonus? = null
    ): OfferCompanyCreateModel? {
        if (discount != null) {
            return OfferCompanyCreateModel(
                type = OfferType.DISCOUNT.name,
                title = discount.title,
                description = discount.description,
                start_date = discount.startDate.toString(),
                end_date = discount.endDate.toString(),
                discount = discount.discount,
            )
        } else if (freeEvery != null) {
            return OfferCompanyCreateModel(
                type = OfferType.STAMP.name,
                title = freeEvery.title,
                description = freeEvery.description,
                start_date = freeEvery.startDate.toString(),
                end_date = freeEvery.endDate.toString(),
                free_every = freeEvery.freeEvery,
            )
        } else if (bonus != null) {
            return OfferCompanyCreateModel(
                type = OfferType.ACCUM.name,
                title = bonus.title,
                description = bonus.description,
                start_date = bonus.startDate.toString(),
                end_date = bonus.endDate.toString(),
                bonus_from_purchase = bonus.bonusFromPurchase,
                bonus_payment_percent = bonus.bonusPaymentPercent
            )
        } else {
            return null
        }
    }
}