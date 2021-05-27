package com.leinaro.mercadolibre_android_example.domain.usecase.get_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.model.Category

interface GetCategoryInteractor {
    suspend fun execute(): Result<List<Category>>
}

