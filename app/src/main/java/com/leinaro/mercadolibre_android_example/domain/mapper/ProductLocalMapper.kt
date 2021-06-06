package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.ProductLocal
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import javax.inject.Inject

class ProductLocalMapper @Inject constructor() : Mapper<ProductLocal, Product> {
    override fun map(input: ProductLocal): Product {
        return Product(
            id = input.productId,
            name = input.title,
            price = input.price,
            image = input.thumbnail,
            pictures = listOf(),
            description = "",
        )
    }
}
