package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.feature.company.data.network.model.ScanQrModel
import com.kotleters.mobile.feature.company.domain.entity.ScanQr

object ScanQrMapper {
    fun toScanQr(scanQrModel: ScanQrModel): ScanQr {
        return ScanQr(
            firstName = scanQrModel.client_first_name,
            lastName = scanQrModel.client_last_name,
            offerTitle = scanQrModel.offer_title,
            discount = scanQrModel.offer_discount
        )
    }
}
