package com.leinaro.mercadolibre_android_example.datasource.remote

import com.leinaro.mercadolibre_android_example.domain.model.Category
import com.leinaro.mercadolibre_android_example.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreServices {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("search")
    suspend fun getProductsByCategory(@Query("category") categoryName: String): List<Product>

}
