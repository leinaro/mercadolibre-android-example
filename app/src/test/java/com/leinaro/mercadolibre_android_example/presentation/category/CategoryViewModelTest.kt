package com.leinaro.mercadolibre_android_example.presentation.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.leinaro.mercadolibre_android_example.MainCoroutineScopeRule
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories.GetCategoriesInteractor
import com.leinaro.mercadolibre_android_example.presentation.category.handler.CategoryViewData
import com.leinaro.mercadolibre_android_example.presentation.category.handler.ShowCategoryList
import com.leinaro.mercadolibre_android_example.presentation.category.handler.ShowCategoryListViewHandler
import com.leinaro.mercadolibre_android_example.presentation.common.DefaultError
import com.leinaro.mercadolibre_android_example.presentation.common.ErrorViewData
import com.leinaro.mercadolibre_android_example.presentation.common.ShowGeneralErrorViewHandler
import com.leinaro.mercadolibre_android_example.presentation.common.ViewDataState
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CategoryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Mock
    private lateinit var getCategoriesInteractor: GetCategoriesInteractor

    @Mock
    private lateinit var mockObserver: Observer<ViewDataState<out CategoryViewData>>

    @Mock
    private lateinit var mockErrorObserver: Observer<ViewDataState<out ErrorViewData>>

    @Captor
    private lateinit var captor: ArgumentCaptor<ViewDataState<ShowCategoryList>>

    @Captor
    private lateinit var errorCaptor: ArgumentCaptor<ViewDataState<DefaultError>>

    private lateinit var viewModel: CategoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CategoryViewModel(savedStateHandle, getCategoriesInteractor)
        viewModel.getViewData().observeForever(mockObserver)
        viewModel.getErrorViewData().observeForever(mockErrorObserver)
    }

    @After
    fun tearDown() {
        viewModel.getViewData().removeObserver(mockObserver)
        viewModel.getErrorViewData().removeObserver(mockErrorObserver)
    }

    @Test
    fun `should return a list of categories when fetching categories successfully`() {
        runBlockingTest {
            // given
            val categories = listOf<Category>()
            val flow: LiveData<Result<List<Category>>> = flow {
                emit(Result.Loading(categories))
                delay(10)
                emit(Result.Success(categories))
            }.asLiveData()
            `when`(getCategoriesInteractor.execute()).thenReturn(flow)

            // when
            viewModel.getCategories()

            // then
            verify(getCategoriesInteractor, times(1)).execute()

            verify(mockObserver).onChanged(captor.capture())
            assertTrue(captor.value.first.isLoading)

            coroutineScope.advanceTimeBy(20)

            verify(mockObserver, times(2)).onChanged(captor.capture())
            assertFalse(captor.value.first.isLoading)

            assertTrue(viewModel.getViewData().value?.first is ShowCategoryList)
            assertTrue(viewModel.getViewData().value?.second is ShowCategoryListViewHandler)
        }
    }

    @Test
    fun `should return an error when fetching categories fails`() {
        runBlockingTest {
            // given
            val categories = listOf<Category>()
            val flow: LiveData<Result<List<Category>>> = flow {
                emit(Result.Loading(categories))
                delay(10)
                emit(Result.Failure(Exception("Test Exception")))
            }.asLiveData()
            `when`(getCategoriesInteractor.execute()).thenReturn(flow)

            // when
            viewModel.getCategories()

            // then
            verify(getCategoriesInteractor, times(1)).execute()

            verify(mockObserver).onChanged(captor.capture())
            assertTrue(captor.value.first.isLoading)

            coroutineScope.advanceTimeBy(20)

            verify(mockErrorObserver, times(1)).onChanged(errorCaptor.capture())
            assertEquals(errorCaptor.value.first.throwable.message, "Test Exception")

            assertTrue(viewModel.getErrorViewData().value?.first is DefaultError)
            assertTrue(viewModel.getErrorViewData().value?.second is ShowGeneralErrorViewHandler)
        }
    }
}