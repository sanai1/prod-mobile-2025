package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.OfferType
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel

object OfferMapper {
    fun toOfferCompanyCreateModel(
        categoryId: Int,
        discount: Company.Discount? = null,
        bonus: Company.Bonus? = null
    ): OfferCompanyCreateModel? {
        if (discount != null) {
            return OfferCompanyCreateModel(
                type = OfferType.DISCOUNT.name,
                title = discount.title,
                description = discount.description,
                start_date = discount.startDate.toString(),
                end_date = discount.endDate.toString(),
                category_id = categoryId,
                discount = discount.discount,
            )
        } else if (bonus != null) {
            return OfferCompanyCreateModel(
                type = OfferType.ACCUM.name,
                title = bonus.title,
                description = bonus.description,
                start_date = bonus.startDate.toString(),
                end_date = bonus.endDate.toString(),
                category_id = categoryId,
                bonus_from_purchase = bonus.bonusFromPurchase,
                bonus_payment_percent = bonus.bonusPaymentPercent
            )
        } else {
            return null
        }
    }
}