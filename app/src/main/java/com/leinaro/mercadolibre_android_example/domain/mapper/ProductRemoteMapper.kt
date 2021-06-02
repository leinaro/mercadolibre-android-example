package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.ProductLocal
import com.leinaro.mercadolibre_android_example.datasource.model.ProductRemote
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import javax.inject.Inject

class ProductRemoteMapper @Inject constructor() : Mapper<ProductRemote, ProductLocal> {
    override fun map(input: ProductRemote): ProductLocal {
        return ProductLocal(
            productId = input.id,
            title = input.title,
            price = input.price,
            thumbnail = input.thumbnail,
            productCategoryId = input.category_id,
        )
    }
}
