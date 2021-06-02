package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<Result<List<Category>>>
    fun getCategory(categoryId: String): Flow<Result<Category>>
}