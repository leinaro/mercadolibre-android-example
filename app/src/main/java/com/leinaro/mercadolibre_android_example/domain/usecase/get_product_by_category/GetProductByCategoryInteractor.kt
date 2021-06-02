package com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductByCategoryInteractor {
    suspend fun execute(categoryId: String, limit: Int? = null): Flow<Result<List<Product>>>
}
