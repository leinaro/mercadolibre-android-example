package com.leinaro.mercadolibre_android_example.datasource.remote

import com.leinaro.mercadolibre_android_example.datasource.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.datasource.model.ProductRemote
import com.leinaro.mercadolibre_android_example.datasource.model.SearchQueryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadoLibreServices {
    @GET("sites/MCO/categories")
    suspend fun getCategories(
        @Query("limit") limit: Int? = 5,
    ): List<CategoryRemote>

    @GET("items/{productId}")
    suspend fun getProduct(
        @Path("productId") productId: String,
    ): ProductRemote

    @GET("items/{productId}/description")
    suspend fun getProductDescription(
        @Path("productId") productId: String,
    ): ProductRemote


    @GET("sites/MCO/search")
    suspend fun getProductsByCategory(
        @Query("category") categoryId: String,
        @Query("limit") limit: Int? = null,
    ): SearchQueryResponse

    @GET("sites/MCO/search")
    suspend fun getProductByQuery(
        @Query("q") q: String,
    ): SearchQueryResponse
}
