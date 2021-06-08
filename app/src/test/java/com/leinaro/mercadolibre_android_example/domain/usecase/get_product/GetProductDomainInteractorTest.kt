package com.leinaro.mercadolibre_android_example.domain.usecase.get_product

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

class GetProductDomainInteractorTest {

    @Mock
    private lateinit var repository: ProductRepository

    private lateinit var getProductInteractor: GetProductInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProductInteractor = GetProductDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with a product with pictures when fetching product by id is successfully`() {
        // given
        val productId = "MCO123456"
        val product = Product(productId, "PS5", "3500000", "", null, null)
        val flow: Flow<Result<Product>> = flow {
            emit(Result.Loading(product))
            delay(10)
            emit(Result.Success(product))
        }

        Mockito.`when`(repository.getProduct(productId)).thenReturn(flow)

        // when
        getProductInteractor.execute(productId)

        //then
        Mockito.verify(repository, Mockito.times(1)).getProduct(productId)
    }
}