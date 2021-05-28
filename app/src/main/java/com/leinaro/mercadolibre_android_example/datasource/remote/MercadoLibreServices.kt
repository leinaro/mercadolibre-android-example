package com.leinaro.mercadolibre_android_example.datasource.remote

import com.leinaro.mercadolibre_android_example.domain.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.domain.model.SearchQueryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreServices {
    @GET("categories")
    suspend fun getCategories(
        @Query("limit") limit: Int? = 5,
    ): List<CategoryRemote>

    @GET("search")
    suspend fun getProductsByCategory(
        @Query("category") categoryName: String,
        @Query("limit") limit: Int? = null,
    ): SearchQueryResponse

}
