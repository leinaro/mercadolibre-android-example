package com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_query

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.ProductRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetProductByQueryInteractor {
    suspend fun execute(query: String): Flow<Result<List<Product>>>
}

class GetProductByQueryDomainInteractor @Inject constructor(
    val repository: ProductRepository,
) : GetProductByQueryInteractor {
    override suspend fun execute(query: String): Flow<Result<List<Product>>> {
        return repository.getProductsByQuery(query)
    }

}
