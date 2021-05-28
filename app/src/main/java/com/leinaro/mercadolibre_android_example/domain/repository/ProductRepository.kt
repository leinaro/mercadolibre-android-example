package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Product

interface ProductRepository {
    suspend fun getProductsByCategory(categoryId: String, limit: Int?): Result<List<Product>>
}
