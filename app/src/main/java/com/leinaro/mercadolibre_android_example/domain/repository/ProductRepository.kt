package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsByCategory(categoryId: String, limit: Int?): Flow<Result<List<Product>>>
    fun getProduct(productId: String): Flow<Result<Product>>
    fun getProductsByQuery(query: String): Flow<Result<List<Product>>>
}
