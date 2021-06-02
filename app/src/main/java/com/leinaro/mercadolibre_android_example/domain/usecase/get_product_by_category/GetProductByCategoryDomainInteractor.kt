package com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.ProductRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByCategoryDomainInteractor @Inject constructor(
    val repository: ProductRepository,
) : GetProductByCategoryInteractor {
    override suspend fun execute(categoryId: String, limit: Int?): Flow<Result<List<Product>>> {
        return repository.getProductsByCategory(categoryId, limit)
    }
}