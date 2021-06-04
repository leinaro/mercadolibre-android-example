package com.leinaro.mercadolibre_android_example.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_query.GetProductByQueryInteractor
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductByQueryInteractor: GetProductByQueryInteractor,
) : BaseViewModel<SearchViewData>() {

    fun getProducts(query: String) {
        viewModelScope.launch {
            getProductByQueryInteractor.execute(query)
                .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
                .observeForever { result ->
                    when (result) {
                        is Result.Success -> showProducts(result.value, false)
                        is Result.Loading -> showProducts(result.value, true)
                        is Result.Failure -> showError(result.throwable)
                    }

                }
        }
    }

    private fun showProducts(products: List<Product>, isLoading: Boolean) {
        setValue(ShowProductSearch(products, isLoading), ShowProductsViewHandler)
    }

}
