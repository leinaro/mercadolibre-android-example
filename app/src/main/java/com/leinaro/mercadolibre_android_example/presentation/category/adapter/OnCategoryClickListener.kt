package com.leinaro.mercadolibre_android_example.presentation.category.adapter

interface OnCategoryClickListener {
    fun onShowMoreClick(categoryId: String)
    fun onProductItemClick(productId: String)
}