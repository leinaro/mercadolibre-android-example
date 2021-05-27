package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.model.Category
import javax.inject.Inject

class Repository @Inject constructor(
    private val mercadolibreServices: MercadoLibreServices,
) : ProductRepository, CategoryRepository {
    override suspend fun getProducts() {

    }

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            Result.Success(mercadolibreServices.getCategories())
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }
}