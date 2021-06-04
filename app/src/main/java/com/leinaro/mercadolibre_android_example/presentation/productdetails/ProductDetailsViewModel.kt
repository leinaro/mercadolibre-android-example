package com.leinaro.mercadolibre_android_example.presentation.productdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product.GetProductInteractor
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.productdetails.handler.ShowProductDetailsViewHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductInteractor: GetProductInteractor,
) : BaseViewModel<ProductDetailsViewData>() {

    init {
        savedStateHandle.get<String>("productId")?.let {
            getProductDetails(it)
        }
    }

    fun getProductDetails(productId: String) {
        viewModelScope.launch {
            getProductInteractor.execute(productId)
                .asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
                .observeForever { result ->
                    when (result) {
                        is Result.Success -> {
                            showProductDetails(result.value, false)
                        }
                        is Result.Loading -> showProductDetails(result.value, true)
                        is Result.Failure -> showError(result.throwable)
                    }
                }
        }
    }

    private fun showProductDetails(product: Product, isLoading: Boolean) {
        setValue(ShowProductDetails(product, isLoading), ShowProductDetailsViewHandler)
    }
}