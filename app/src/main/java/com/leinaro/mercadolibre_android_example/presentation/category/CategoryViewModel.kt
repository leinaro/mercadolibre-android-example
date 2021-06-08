package com.leinaro.mercadolibre_android_example.presentation.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories.GetCategoriesInteractor
import com.leinaro.mercadolibre_android_example.presentation.category.handler.CategoryViewData
import com.leinaro.mercadolibre_android_example.presentation.category.handler.ShowCategoryList
import com.leinaro.mercadolibre_android_example.presentation.category.handler.ShowCategoryListViewHandler
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val getCategoriesInteractor: GetCategoriesInteractor,
) : BaseViewModel<CategoryViewData>() {

    private var viewDataCache: ShowCategoryList? = null

    fun getCategories() {
        if (viewDataCache != null) {
            setValue(viewDataCache!!, ShowCategoryListViewHandler)
            return
        }
        viewModelScope.launch {
            getCategoriesInteractor.execute()
                .observeForever { result ->
                    when (result) {
                        is Result.Success -> showCategoryList(result.value, false)
                        is Result.Loading -> showCategoryList(result.value, true)
                        is Result.Failure -> showError(result.throwable)
                    }
                }
        }
    }

    private fun showCategoryList(categories: List<Category>, isLoading: Boolean) {
        val data = ShowCategoryList(categories, isLoading)
        viewDataCache = data
        setValue(data, ShowCategoryListViewHandler)
    }

}
