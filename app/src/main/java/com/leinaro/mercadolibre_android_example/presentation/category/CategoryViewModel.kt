package com.leinaro.mercadolibre_android_example.presentation.category

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoriesInteractor
import com.leinaro.mercadolibre_android_example.presentation.category.handler.ShowCategoryListViewHandler
import com.leinaro.mercadolibre_android_example.presentation.commons.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val getCategoriesInteractor: GetCategoriesInteractor,
) : BaseViewModel<CategoryViewData>() {

    fun getCategories() {
        viewModelScope.launch {
            when (val result = getCategoriesInteractor.execute()) {
                is Result.Success -> showCategoryList(result.value)

                is Result.Failure -> {
                    Log.e("iarl", "---" + result.throwable.printStackTrace())

                }
            }
        }
    }

    private fun showCategoryList(categories: List<Category>) {
        setValue(ShowCategoryList(categories), ShowCategoryListViewHandler)
    }

}
