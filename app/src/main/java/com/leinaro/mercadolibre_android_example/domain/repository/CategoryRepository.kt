package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
}