package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.CategoryWithProducts
import com.leinaro.mercadolibre_android_example.datasource.model.ProductLocal
import com.leinaro.mercadolibre_android_example.domain.common.ListMapperImpl
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import javax.inject.Inject

class CategoryWithProductsMapper @Inject constructor(
    val productLocalMapper: Mapper<ProductLocal, Product>,
) :
    Mapper<CategoryWithProducts, Category> {
    override fun map(input: CategoryWithProducts): Category {
        return Category(
            id = input.category.categoryId,
            name = input.category.name,
            products = ListMapperImpl(productLocalMapper).map(input.products)
        )
    }


}
