package com.kotleters.mobile.common.category.data

import com.kotleters.mobile.common.category.data.network.CategoryInfoClient
import com.kotleters.mobile.common.category.domain.CategoryInfo
import com.kotleters.mobile.common.category.domain.CategoryInfoRepository
import com.kotleters.mobile.common.data.network.model.ResponseTemplate

class CategoryInfoRepositoryImpl : CategoryInfoRepository {
    override suspend fun getCategories(): ResponseTemplate<CategoryInfo> {
        try {
            val call = CategoryInfoClient.categoryInfoService.getCategories().execute()
            return if (call.code() == 200) {
                val categoryInfoModel = call.body()!!
                ResponseTemplate.Success(
                    data = CategoryInfo(
                        id = categoryInfoModel.id,
                        category = categoryInfoModel.category,
                        subcategory = categoryInfoModel.subcategory
                    )
                )
            } else {
                ResponseTemplate.Error(message = call.message())
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }
}
