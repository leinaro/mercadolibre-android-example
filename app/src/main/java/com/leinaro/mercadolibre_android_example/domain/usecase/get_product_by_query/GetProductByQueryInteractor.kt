package com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_query

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductByQueryInteractor {
    fun execute(query: String): Flow<Result<List<Product>>>
}

