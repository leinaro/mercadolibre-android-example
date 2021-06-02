package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.datasource.local.CategoryDao
import com.leinaro.mercadolibre_android_example.datasource.local.DataBaseClient
import com.leinaro.mercadolibre_android_example.datasource.local.ProductDao
import com.leinaro.mercadolibre_android_example.datasource.model.*
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.common.ListMapperImpl
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val mercadolibreServices: MercadoLibreServices,
    private val mercadoLibreDataBase: DataBaseClient,
    private val picturesRemoteMapper: Mapper<PicturesRemote, PictureLocal>,
    private val productRemoteMapper: Mapper<ProductRemote, ProductLocal>,
    private val productLocalMapper: Mapper<ProductLocal, Product>,
    private val categoryRemoteMapper: Mapper<CategoryRemote, CategoryLocal>,
    private val categoryLocalMapper: Mapper<CategoryLocal, Category>,
    private val categoryWithProductsLocalMapper: Mapper<CategoryWithProducts, Category>,
    private val productWithPicturesLocalMapper: Mapper<ProductWithPictures, Product>,
) : CategoryRepository, ProductRepository {

    private val categoryDao: CategoryDao =
        mercadoLibreDataBase.db.categoryDao()
    private val productDao: ProductDao =
        mercadoLibreDataBase.db.productDao()

    override fun getCategory(categoryId: String): Flow<Result<Category>> {
        return flow {
            emit(
                Result.Loading(
                    categoryLocalMapper.map(
                        categoryDao.getCategoryById(categoryId)
                    )
                )
            )
            try {
                val categories = mercadolibreServices.getCategories()
                insertRemoteCategoriesToDataBase(categories)

                emit(
                    Result.Success(
                        categoryLocalMapper.map(
                            categoryDao.getCategoryById(categoryId)
                        )
                    )
                )
            } catch (exception: IOException) {
                emit(
                    Result.Failure(
                        exception,
                        categoryLocalMapper.map(
                            categoryDao.getCategoryById(categoryId)
                        )
                    )
                )
            }
        }
    }

    override fun getCategories(): Flow<Result<List<Category>>> {
        return flow {
            emit(
                Result.Loading(
                    ListMapperImpl(categoryWithProductsLocalMapper).map(
                        categoryDao.getCategoryWithProducts()
                    )
                )
            )
            try {
                val categories = mercadolibreServices.getCategories()
                insertRemoteCategoriesToDataBase(categories)

                emit(
                    Result.Loading(
                        ListMapperImpl(categoryWithProductsLocalMapper).map(
                            categoryDao.getCategoryWithProducts()
                        )
                    )
                )

                categories.map {
                    val productsByCategory =
                        mercadolibreServices.getProductsByCategory(it.id).results
                            .map { product ->
                                product.category_id = it.id
                                product
                            }

                    insertRemoteProductsToDatabase(productsByCategory)
                }
                emit(
                    Result.Success(
                        ListMapperImpl(categoryWithProductsLocalMapper).map(
                            categoryDao.getCategoryWithProducts()
                        )
                    )
                )
            } catch (exception: IOException) {
                emit(
                    Result.Failure(
                        exception,
                        ListMapperImpl(categoryWithProductsLocalMapper).map(
                            categoryDao.getCategoryWithProducts()
                        )
                    )
                )
            }
        }
    }

    override suspend fun getProductsByCategory(
        categoryId: String,
        limit: Int?,
    ): Flow<Result<List<Product>>> {
        return flow {
            emit(
                Result.Loading(
                    ListMapperImpl(productLocalMapper)
                        .map(
                            productDao.getProductsByCategory(categoryId)
                        )
                )
            )
            try {
                val products = mercadolibreServices.getProductsByCategory(categoryId).results
                    .map { product ->
                        product.category_id = categoryId
                        product
                    }
                insertRemoteProductsToDatabase(products)

                emit(
                    Result.Success(
                        ListMapperImpl(productLocalMapper)
                            .map(
                                productDao.getProductsByCategory(categoryId)
                            )
                    )
                )

            } catch (exception: IOException) {
                emit(
                    Result.Failure(
                        exception,
                        ListMapperImpl(productLocalMapper)
                            .map(
                                productDao.getProductsByCategory(categoryId)
                            )
                    )
                )
            }
        }
    }

    override suspend fun getProduct(productId: String): Flow<Result<Product>> {
        return flow {
            emit(
                Result.Loading(
                    productWithPicturesLocalMapper.map(
                        productDao.getProduct(productId)
                    )
                )
            )
            try {
                val product = mercadolibreServices.getProduct(productId)
                updateRemoteProductToDatabase(product)

                emit(
                    Result.Success(
                        productWithPicturesLocalMapper.map(
                            productDao.getProduct(productId)
                        )
                    )
                )

            } catch (exception: IOException) {
                emit(
                    Result.Failure(
                        exception,
                        productWithPicturesLocalMapper.map(
                            productDao.getProduct(productId)
                        )
                    )
                )
            }
        }
    }

    private fun insertRemoteCategoriesToDataBase(
        categories: List<CategoryRemote>,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            ListMapperImpl(categoryRemoteMapper)
                .map(categories).apply {
                    categoryDao.insertAllCategories(this)
                }
        }
    }

    private fun updateRemoteProductToDatabase(product: ProductRemote) {
        CoroutineScope(Dispatchers.IO).launch {
            productRemoteMapper.map(product)
                .apply {
                    productDao.insertProduct(this)
                }

            ListMapperImpl(picturesRemoteMapper).map(
                product.pictures.orEmpty().map {
                    it.productId = product.id
                    it
                }
            ).apply {
                productDao.insertPictures(this)
            }
        }
    }

    private fun insertRemoteProductsToDatabase(products: List<ProductRemote>) {
        CoroutineScope(Dispatchers.IO).launch {
            ListMapperImpl(productRemoteMapper)
                .map(products)
                .apply {
                    productDao.insertAllProducts(this)
                }
        }
    }
}