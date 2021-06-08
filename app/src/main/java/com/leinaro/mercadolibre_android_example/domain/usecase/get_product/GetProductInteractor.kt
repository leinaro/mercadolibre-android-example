package com.leinaro.mercadolibre_android_example.domain.usecase.get_product

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductInteractor {
    fun execute(productId: String): Flow<Result<Product>>
}

