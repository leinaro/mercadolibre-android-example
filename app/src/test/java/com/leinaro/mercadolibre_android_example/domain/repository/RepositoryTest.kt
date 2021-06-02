package com.leinaro.mercadolibre_android_example.domain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Test
    fun `should return a Flow with a list of categories when fetching categories successfully`() {
        runBlocking {
            // given
            val categoryWithProducts = CategoryWithProducts(
                category = CategoryLocal("1", "category"),
                products = listOf()
            )

            val categoryRemote = CategoryRemote(
                id = "1",
                name = "category"
            )

            val categoryLocal = CategoryLocal(
                categoryId = "1",
                name = "category"
            )

            val category = Category("1", "category", listOf())

            val categoryRemoteList = listOf(categoryRemote)

            `when`(categoryDao.getCategoryWithProducts()).thenReturn(listOf())
            `when`(categoryWithProductsLocalMapper.map(categoryWithProducts)).thenReturn(category)

            `when`(mercadolibreServices.getCategories()).thenReturn(categoryRemoteList)
            `when`(categoryRemoteMapper.map(categoryRemote)).thenReturn(categoryLocal)
            //   doNothing().`when`(categoryDao.insertAllCategories(listOf(categoryLocal)))

            // when

            val actual = mutableListOf<Result<List<Category>>>()

            repository.getCategories()
                .take(3).collect {
                    actual.add(it)
                }

            assertTrue(actual[0] is Result.Loading)
            assertTrue(actual[1] is Result.Loading)
            assertTrue(actual[2] is Result.Success)
            //then

            //   assertTrue(firstItem is Result.Loading) // Using AssertJ

            // Mockito.verify(categoryDao, Mockito.times(3)).getCategoryWithProducts()
            // Mockito.verify(mercadolibreServices, Mockito.times(1)).getCategories()
        }
    }
}