package com.leinaro.mercadolibre_android_example.presentation.productdetails

import com.leinaro.mercadolibre_android_example.presentation.model.Product

sealed class ProductDetailsViewData
data class ShowProductDetails(
    val product: Product,
    val isLoading: Boolean,
) : ProductDetailsViewData()