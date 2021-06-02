package com.leinaro.mercadolibre_android_example.presentation.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoryInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category.GetProductByCategoryInteractor
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.handler.ShowCategoryViewHandler
import com.leinaro.mercadolibre_android_example.presentation.product.handler.ShowProductListViewHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCategoryInteractor: GetCategoryInteractor,
    private val getProductsByCategoryInteractor: GetProductByCategoryInteractor,
) : BaseViewModel<ProductViewData>() {

    private var categoryViewDataCache: ShowCategoryDetails? = null
    private var productsViewDataCache: ShowProductList? = null

    init {
        savedStateHandle.get<String>("categoryId")?.let {
            getCategory(it)
        }
    }

    fun getCategory(categoryId: String) {
        if (categoryViewDataCache != null) {
            setValue(categoryViewDataCache!!, ShowCategoryViewHandler)
            return
        }

        viewModelScope.launch {
            getCategoryInteractor.execute(categoryId)
                .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
                .observeForever { result ->
                    when (result) {
                        is Result.Success -> showCategoryDetails(result.value, false)
                        is Result.Loading -> showCategoryDetails(result.value, true)
                        is Result.Failure -> showError(result.throwable)
                    }
                }
        }
    }

    fun getProductsByCategory(categoryId: String) {
        if (productsViewDataCache != null) {
            setValue(productsViewDataCache!!, ShowProductListViewHandler)
            return
        }
        viewModelScope.launch {
            getProductsByCategoryInteractor.execute(categoryId)
                .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
                .observeForever { result ->
                    when (result) {
                        is Result.Success -> showProductList(result.value, false)
                        is Result.Loading -> showProductList(result.value, true)
                        is Result.Failure -> showError(result.throwable)
                    }
                }
        }
    }

    private fun showCategoryDetails(category: Category, isLoading: Boolean) {
        val data = ShowCategoryDetails(category, isLoading)
        categoryViewDataCache = data
        setValue(data, ShowCategoryViewHandler)
    }

    private fun showProductList(products: List<Product>, isLoading: Boolean) {
        val data = ShowProductList(products, isLoading)
        productsViewDataCache = data
        setValue(data, ShowProductListViewHandler)
    }

}
