package com.leinaro.mercadolibre_android_example.domain.usecase.get_category

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryDomainInteractor @Inject constructor(
    val repository: CategoryRepository,
) : GetCategoryInteractor {
    override fun execute(categoryId: String): Flow<Result<Category>> {
        return repository.getCategory(categoryId)
    }
}