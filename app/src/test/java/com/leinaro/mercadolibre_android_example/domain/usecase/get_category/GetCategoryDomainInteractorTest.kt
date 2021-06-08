package com.leinaro.mercadolibre_android_example.domain.usecase.get_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCategoryDomainInteractorTest {

    @Mock
    private lateinit var repository: CategoryRepository

    private lateinit var getCategoryInteractor: GetCategoryInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCategoryInteractor = GetCategoryDomainInteractor(repository)

    }

    @Test
    fun `should return a Flow with a category with its products when fetching category by id with its products is successfully`() {
        // given
        val categoryId = "MCO123456"
        val category = Category("categoryId", "categoryName", listOf())

        val flow: Flow<Result<Category>> = flow {
            emit(Result.Loading(category))
            delay(10)
            emit(Result.Success(category))
        }

        Mockito.`when`(repository.getCategory(categoryId)).thenReturn(flow)

        // when
        getCategoryInteractor.execute(categoryId)

        //then
        Mockito.verify(repository, Mockito.times(1)).getCategory(categoryId)
    }
}