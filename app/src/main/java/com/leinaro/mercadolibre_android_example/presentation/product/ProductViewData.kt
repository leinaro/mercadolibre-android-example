package com.leinaro.mercadolibre_android_example.presentation.product

import com.leinaro.mercadolibre_android_example.presentation.model.Product

sealed class ProductViewData
data class ShowProductList(val products: List<Product>) : ProductViewData()
