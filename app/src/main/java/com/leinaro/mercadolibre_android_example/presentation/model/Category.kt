package com.leinaro.mercadolibre_android_example.presentation.model


class Category(
    val id: String,
    val name: String,
    var products: List<Product>,
) {
    override fun toString(): String {
        return name + " - " + products.size
    }
}
