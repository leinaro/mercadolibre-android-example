package com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category

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

class GetProductByCategoryDomainInteractorTest {

    @Mock
    private lateinit var repository: ProductRepository

    private lateinit var getProductByCategoryInteractor: GetProductByCategoryInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProductByCategoryInteractor = GetProductByCategoryDomainInteractor(repository)
    }

    @Test
    fun `should return a Flow with a list of product when fetching product by categoryId is successfully`() {
        // given
        val categoryId = "MCO123456"
        val product = Product("MCO987654", "PS5", "3500000", "", null, null)
        val flow: Flow<Result<List<Product>>> = flow {
            emit(Result.Loading(listOf(product)))
            delay(10)
            emit(Result.Success(listOf(product)))
        }

        Mockito.`when`(repository.getProductsByCategory(categoryId, null)).thenReturn(flow)

        // when
        getProductByCategoryInteractor.execute(categoryId)

        //then
        Mockito.verify(repository, Mockito.times(1)).getProductsByCategory(categoryId, null)
    }
}