package com.leinaro.mercadolibre_android_example.domain.usecase.get_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import javax.inject.Inject

class GetCategoriesDomainInteractor @Inject constructor(
    val repository: CategoryRepository,
) : GetCategoriesInteractor {
    override suspend fun execute(): Result<List<Category>> {
        return repository.getCategories()
    }
}