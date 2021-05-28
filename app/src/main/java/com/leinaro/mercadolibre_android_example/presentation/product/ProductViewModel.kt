package com.leinaro.mercadolibre_android_example.presentation.product

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category.GetProductByCategoryInteractor
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.handler.ShowProductListViewHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val getProductsByCategoryInteractor: GetProductByCategoryInteractor,
) : BaseViewModel<ProductViewData>() {

    init {
        Log.e("iarl", savedStateHandle.keys().toString())
        //  getCategories()
    }

    fun getProductsByCategory(categoryId: String) {
        viewModelScope.launch {
            when (val result = getProductsByCategoryInteractor.execute(categoryId)) {
                is Result.Success -> showProductList(result.value)

                is Result.Failure -> {
                    Log.e("iarl", "---" + result.throwable.printStackTrace())

                }
            }
        }
    }

    private fun showProductList(products: List<Product>) {
        setValue(ShowProductList(products), ShowProductListViewHandler)
    }

}
