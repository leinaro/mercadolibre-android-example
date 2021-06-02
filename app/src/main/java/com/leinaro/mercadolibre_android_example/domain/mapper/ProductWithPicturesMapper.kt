package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.ProductWithPictures
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import javax.inject.Inject

class ProductWithPicturesMapper @Inject constructor() :
    Mapper<ProductWithPictures, Product> {
    override fun map(input: ProductWithPictures): Product {
        return Product(
            id = input.product.productId,
            name = input.product.title,
            price = input.product.price,
            image = input.product.thumbnail,
            pictures = input.pictures.map { it.url }
        )
    }


}
