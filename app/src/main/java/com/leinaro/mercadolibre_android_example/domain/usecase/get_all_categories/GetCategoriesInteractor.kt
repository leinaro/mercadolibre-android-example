package com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories

import androidx.lifecycle.LiveData
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.presentation.model.Category

interface GetCategoriesInteractor {
    fun execute(): LiveData<Result<List<Category>>>
}

