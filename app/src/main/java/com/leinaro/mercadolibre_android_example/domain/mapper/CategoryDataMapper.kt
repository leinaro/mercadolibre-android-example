package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.domain.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import javax.inject.Inject

class CategoryDataMapper @Inject constructor() :
    Mapper<CategoryRemote, Category> {
    override fun map(input: CategoryRemote): Category {
        return Category(
            id = input.id,
            name = input.name,
            products = listOf()
        )
    }
}