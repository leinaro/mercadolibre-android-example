package com.leinaro.mercadolibre_android_example.domain.usecase.get_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.model.Category
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryDomainInteractor @Inject constructor(
    val repository: CategoryRepository,
) : GetCategoryInteractor {
    override suspend fun execute(): Result<List<Category>> {
        return repository.getCategories()
    }
}