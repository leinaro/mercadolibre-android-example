package com.leinaro.mercadolibre_android_example.domain.usecase.get_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoryInteractor {
    fun execute(categoryId: String): Flow<Result<Category>>
}

