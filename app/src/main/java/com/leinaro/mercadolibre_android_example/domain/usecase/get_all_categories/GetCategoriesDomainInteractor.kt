package com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesDomainInteractor @Inject constructor(
    val repository: CategoryRepository,
) : GetCategoriesInteractor {
    override fun execute(): LiveData<Result<List<Category>>> {
        return repository.getCategories().asLiveData(Dispatchers.IO)
    }
}