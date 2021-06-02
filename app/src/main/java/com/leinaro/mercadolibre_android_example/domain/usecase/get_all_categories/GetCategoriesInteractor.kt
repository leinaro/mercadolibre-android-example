package com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoriesInteractor {
    fun execute(): Flow<Result<List<Category>>>
}

