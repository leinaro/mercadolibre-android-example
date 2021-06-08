package com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_query

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.ProductRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetProductByQueryDomainInteractorTest {
    @Mock
    private lateinit var repository: ProductRepository

    private lateinit var getProductByQueryInteractor: GetProductByQueryInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProductByQueryInteractor = GetProductByQueryDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with a product with pictures when fetching product by id is successfully`() {
        // given
        val query = "PS5"
        val product = Product("productId", "PS5", "3500000", "", null, null)
        val flow: Flow<Result<List<Product>>> = flow {
            emit(Result.Loading(listOf(product)))
            delay(10)
            emit(Result.Success(listOf(product)))
        }

        Mockito.`when`(repository.getProductsByQuery(query)).thenReturn(flow)

        // when
        getProductByQueryInteractor.execute(query)

        //then
        Mockito.verify(repository, Mockito.times(1)).getProductsByQuery(query)
    }
}