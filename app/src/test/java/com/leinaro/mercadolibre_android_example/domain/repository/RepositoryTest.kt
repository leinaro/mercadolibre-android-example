package com.leinaro.mercadolibre_android_example.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.leinaro.mercadolibre_android_example.MainCoroutineScopeRule
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.datasource.local.AppDataBase
import com.leinaro.mercadolibre_android_example.datasource.local.CategoryDao
import com.leinaro.mercadolibre_android_example.datasource.local.DataBaseClient
import com.leinaro.mercadolibre_android_example.datasource.local.ProductDao
import com.leinaro.mercadolibre_android_example.datasource.model.*
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var mercadolibreServices: MercadoLibreServices

    @Mock
    private lateinit var mercadoLibreDataBase: DataBaseClient

    @Mock
    private lateinit var picturesRemoteMapper: Mapper<PicturesRemote, PictureLocal>

    @Mock
    private lateinit var productRemoteMapper: Mapper<ProductRemote, ProductLocal>

    @Mock
    private lateinit var productLocalMapper: Mapper<ProductLocal, Product>

    @Mock
    private lateinit var categoryRemoteMapper: Mapper<CategoryRemote, CategoryLocal>

    @Mock
    private lateinit var categoryLocalMapper: Mapper<CategoryLocal, Category>

    @Mock
    private lateinit var categoryWithProductsLocalMapper: Mapper<CategoryWithProducts, Category>

    @Mock
    private lateinit var productWithPicturesLocalMapper: Mapper<ProductWithPictures, Product>

    @Mock
    private lateinit var dataBase: AppDataBase

    @Mock
    private lateinit var categoryDao: CategoryDao

    @Mock
    private lateinit var productDao: ProductDao

    private lateinit var repository: Repository

    @Mock
    private lateinit var mockObserver: Observer<Result<List<Category>>>

    @Captor
    private lateinit var captor: ArgumentCaptor<Result<List<Category>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(mercadoLibreDataBase.db).thenReturn(dataBase)
        `when`(dataBase.categoryDao()).thenReturn(categoryDao)
        `when`(dataBase.productDao()).thenReturn(productDao)

        repository = Repository(
            mercadolibreServices,
            mercadoLibreDataBase,
            picturesRemoteMapper,
            productRemoteMapper,
            productLocalMapper,
            categoryRemoteMapper,
            categoryLocalMapper,
            categoryWithProductsLocalMapper,
            productWithPicturesLocalMapper
        )
    }

}