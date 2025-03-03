package com.kotleters.mobile.feature.company.data.network.mapper

import com.kotleters.mobile.feature.company.data.network.model.CompanyProfileModel
import com.kotleters.mobile.feature.company.domain.entity.CompanyProfile

object CompanyProfileMapper {
    fun toCompanyProfile(companyProfileModel: CompanyProfileModel): CompanyProfile {
        return CompanyProfile(
            id = companyProfileModel.id,
            name = companyProfileModel.name,
            email = companyProfileModel.email,
            photoUrl = "http://prod-team-10-avk8n3cp.final.prodcontest.ru/api/company/${companyProfileModel.id}/image",
            categoryId = companyProfileModel.category_id,
            category = companyProfileModel.category,
            subcategory = companyProfileModel.subcategory,
        )
    }
}
