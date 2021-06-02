package com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class GetCategoriesDomainInteractorTest {

    @Mock
    private lateinit var repository: CategoryRepository

    private lateinit var getCategoriesInteractor: GetCategoriesInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCategoriesInteractor = GetCategoriesDomainInteractor(repository)

    }

    @Test
    fun `should return a Flow with a list of categories when fetching categories successfully`() {
        // given
        val flow: Flow<Result<List<Category>>> = flow {
            emit(Result.Loading(listOf<Category>()))
            delay(10)
            emit(Result.Success(listOf<Category>()))
        }

        `when`(repository.getCategories()).thenReturn(flow)

        // when
        getCategoriesInteractor.execute()

        //then
        verify(repository, times(1)).getCategories()


    }

}
