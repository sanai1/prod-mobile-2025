package com.kotleters.mobile.common.category.data

import com.kotleters.mobile.common.category.data.network.CategoryInfoClient
import com.kotleters.mobile.common.category.domain.CategoryInfo
import com.kotleters.mobile.common.category.domain.CategoryInfoRepository
import com.kotleters.mobile.common.data.network.model.ResponseTemplate

class CategoryInfoRepositoryImpl : CategoryInfoRepository {
    override suspend fun getCategories(): ResponseTemplate<List<CategoryInfo>> {
        try {
            val call = CategoryInfoClient.categoryInfoService.getCategories().execute()
            return if (call.code() == 200) {
                ResponseTemplate.Success(
                    data = call.body()!!.map {
                        CategoryInfo(
                            id = it.id,
                            category = it.category,
                            subcategory = it.subcategory
                        )
                    }
                )
            } else {
                ResponseTemplate.Error(message = call.message())
            }
        } catch (e: Exception) {
            return ResponseTemplate.Error(message = e.message.toString())
        }
    }
}
