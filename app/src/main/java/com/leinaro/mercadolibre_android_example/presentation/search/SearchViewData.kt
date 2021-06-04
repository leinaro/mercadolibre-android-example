package com.leinaro.mercadolibre_android_example.presentation.search

import com.leinaro.mercadolibre_android_example.presentation.model.Product

sealed class SearchViewData
data class ShowProductSearch(
    val products: List<Product>,
    val isLoading: Boolean,
) : SearchViewData()