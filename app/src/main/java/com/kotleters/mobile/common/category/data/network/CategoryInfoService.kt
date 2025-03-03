package com.kotleters.mobile.common.category.data.network

import retrofit2.Call
import retrofit2.http.GET

interface CategoryInfoService {
    @GET("categories")
    fun getCategories(): Call<List<CategoryInfoModel>>
}
