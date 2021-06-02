package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.CategoryLocal
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import javax.inject.Inject

class CategoryLocalMapper @Inject constructor() :
    Mapper<CategoryLocal, Category> {

    override fun map(input: CategoryLocal): Category {
        return Category(
            id = input.categoryId,
            name = input.name,
            products = listOf()
        )


    }
}
