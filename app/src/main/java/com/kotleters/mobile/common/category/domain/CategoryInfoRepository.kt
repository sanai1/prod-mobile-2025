package com.kotleters.mobile.common.category.domain

import com.kotleters.mobile.common.data.network.model.ResponseTemplate

interface CategoryInfoRepository {
    suspend fun getCategories(): ResponseTemplate<List<CategoryInfo>>
}
