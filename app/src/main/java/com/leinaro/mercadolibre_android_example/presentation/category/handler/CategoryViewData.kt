package com.leinaro.mercadolibre_android_example.presentation.category.handler

import com.leinaro.mercadolibre_android_example.presentation.model.Category

sealed class CategoryViewData
data class ShowCategoryList(
    val categories: List<Category>,
    val isLoading: Boolean,
) : CategoryViewData()
