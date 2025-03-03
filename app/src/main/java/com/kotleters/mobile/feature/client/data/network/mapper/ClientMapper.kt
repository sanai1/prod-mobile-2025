package com.kotleters.mobile.feature.client.data.network.mapper

import com.kotleters.mobile.feature.client.data.network.model.ClientProfileModel
import com.kotleters.mobile.feature.client.domain.entity.ClientProfile
import com.kotleters.mobile.feature.client.domain.entity.TargetInfo

object ClientMapper {
    fun toClientProfile(clientProfileModel: ClientProfileModel): ClientProfile {
        return ClientProfile(
            id = clientProfileModel.id,
            firstName = clientProfileModel.first_name,
            lastName = clientProfileModel.last_name,
            email = clientProfileModel.email,
            bonus = clientProfileModel.bonus,
            targetInfo = if (clientProfileModel.age != null && clientProfileModel.gender != null) {
                TargetInfo(
                    age = clientProfileModel.age,
                    gender = TargetInfo.Gender.valueOf(clientProfileModel.gender)
                )
            } else {
                null
            },
        )
    }
}
