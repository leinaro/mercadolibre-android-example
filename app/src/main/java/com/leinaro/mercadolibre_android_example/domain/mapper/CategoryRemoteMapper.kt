package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.CategoryLocal
import com.leinaro.mercadolibre_android_example.datasource.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import javax.inject.Inject

class CategoryRemoteMapper @Inject constructor() :
    Mapper<CategoryRemote, CategoryLocal> {
    override fun map(input: CategoryRemote): CategoryLocal {
        return CategoryLocal(
            categoryId = input.id,
            name = input.name
        )
    }
}