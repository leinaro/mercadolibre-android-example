package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.domain.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.domain.model.ProductRemote
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import javax.inject.Inject

class Repository @Inject constructor(
    private val mercadolibreServices: MercadoLibreServices,
    private val productDataMapper: Mapper<ProductRemote, Product>,
    private val categoryDataMapper: Mapper<CategoryRemote, Category>,
) : ProductRepository, CategoryRepository {

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            Result.Success(mapCategories(mercadolibreServices.getCategories()))
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

    override suspend fun getProductsByCategory(
        categoryId: String,
        limit: Int?,
    ): Result<List<Product>> {
        return try {
            Result.Success(mapProducts(mercadolibreServices.getProductsByCategory(
                categoryId, limit
            ).results))
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

    private suspend fun mapCategories(
        categories: List<CategoryRemote>,
    ): List<Category> {
        return categories.map {
            val products = mapProducts(mercadolibreServices.getProductsByCategory(
                categoryName = it.id,
                limit = 5,
            ).results)

            val category = categoryDataMapper.map(it)
            category.products = products



            category
        }
    }

    private fun mapProducts(networkProductList: List<ProductRemote>): List<Product> {
        return networkProductList.map {
            productDataMapper.map(it)
        }
    }
}