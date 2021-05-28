package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.domain.model.ProductRemote
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import javax.inject.Inject

class ProductDataMapper @Inject constructor() : Mapper<ProductRemote, Product> {
    override fun map(input: ProductRemote): Product {
        return Product(
            id = input.id,
            name = input.title,
            price = input.price.toString(),
            image = input.thumbnail
        )
    }
}