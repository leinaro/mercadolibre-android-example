package com.leinaro.mercadolibre_android_example.presentation.product

import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product

sealed class ProductViewData
data class ShowCategoryDetails(val category: Category, val isLoading: Boolean) : ProductViewData()
data class ShowProductList(val products: List<Product>, val isLoading: Boolean) : ProductViewData()
