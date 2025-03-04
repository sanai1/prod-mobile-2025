package com.kotleters.mobile.feature.company.data.network.mapper

import android.util.Log
import com.kotleters.mobile.common.domain.Company
import com.kotleters.mobile.common.domain.OfferType
import com.kotleters.mobile.feature.company.data.network.model.OfferCompanyCreateModel
import java.time.ZoneId

object OfferMapper {
    fun toOfferCompanyCreateModel(
        discount: Company.Discount? = null,
        bonus: Company.Bonus? = null
    ): OfferCompanyCreateModel? {
        val zone = ZoneId.systemDefault()
        if (discount != null) {
            Log.d("HHH", discount.startDate.atZone(zone).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime().toString())
            return OfferCompanyCreateModel(
                type = OfferType.DISCOUNT.name,
                title = discount.title,
                description = discount.description,
                start_date = discount.startDate.atZone(zone).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime().toString(),
                end_date = discount.endDate.atZone(zone).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime().toString(),
                discount = discount.discount,
            )
        } else if (bonus != null) {
            return OfferCompanyCreateModel(
                type = OfferType.ACCUM.name,
                title = bonus.title,
                description = bonus.description,
                start_date = bonus.startDate.atZone(zone).withZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime().toString(),
                end_date = bonus.endDate.atZone(zone).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime().toString(),
                bonus_from_purchase = bonus.bonusFromPurchase,
                bonus_payment_percent = bonus.bonusPaymentPercent
            )
        } else {
            return null
        }
    }
}