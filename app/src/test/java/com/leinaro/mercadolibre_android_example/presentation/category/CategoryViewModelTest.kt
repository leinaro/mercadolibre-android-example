package com.leinaro.mercadolibre_android_example.presentation.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.leinaro.mercadolibre_android_example.MainCoroutineScopeRule
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories.GetCategoriesInteractor
import com.leinaro.mercadolibre_android_example.presentation.common.ErrorViewData
import com.leinaro.mercadolibre_android_example.presentation.common.ViewDataState
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
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
    private lateinit var mockObserver: Observer<ViewDataState<CategoryViewData>>

    @Mock
    private lateinit var mockErrorObserver: Observer<ViewDataState<ErrorViewData>>

    @Captor
    private lateinit var captor: ArgumentCaptor<ViewDataState<CategoryViewData>>

    @Captor
    private lateinit var errorCaptor: ArgumentCaptor<ViewDataState<ErrorViewData>>

    private lateinit var viewModel: CategoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CategoryViewModel(savedStateHandle, getCategoriesInteractor)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun `should return a list of categories when fetching categories successfully`() {
        runBlockingTest {
            // given
            val flow: Flow<Result<List<Category>>> = flow {
                emit(Result.Loading(listOf<Category>()))
                delay(10)
                emit(Result.Success(listOf<Category>()))
            }

            `when`(getCategoriesInteractor.execute()).thenReturn(flow)
            val liveData = viewModel.getViewData()
            liveData.observeForever(mockObserver)

            // when
            viewModel.getCategories()

            // then
            verify(mockObserver).onChanged(captor.capture())
            var viewData = captor.value.first as ShowCategoryList
            assertEquals(true, viewData.isLoading)

            coroutineScope.advanceTimeBy(10)
            verify(mockObserver, times(2)).onChanged(captor.capture())
            viewData = captor.value.first as ShowCategoryList
            assertEquals(false, viewData.isLoading)
        }
    }

    @Test
    fun `should return an error when fetching categories fails`() {
        runBlocking {
            // given
            val flow: Flow<Result<List<Category>>> = flow {
                emit(Result.Loading(listOf<Category>()))
                delay(10)
                emit(Result.Failure(Exception("Test Exception")))
            }

            `when`(getCategoriesInteractor.execute()).thenReturn(flow)
            val liveData = viewModel.getViewData()
            liveData.observeForever(mockObserver)

            val errorLiveData = viewModel.getErrorViewData()
            errorLiveData.observeForever(mockErrorObserver)

            // when
            viewModel.getCategories()

            // then
            verify(mockObserver, times(1)).onChanged(captor.capture())
            var viewData = captor.value.first as ShowCategoryList
            assertEquals(true, viewData.isLoading)

            coroutineScope.advanceTimeBy(10)
            verify(mockErrorObserver, times(1)).onChanged(errorCaptor.capture())
            val errorViewData = errorCaptor.value.first as ErrorViewData
            assertEquals("Test Exception", errorViewData.error.message)
        }
    }
}