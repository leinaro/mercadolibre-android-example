package com.leinaro.mercadolibre_android_example.datasource.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithProducts(
    @Embedded val category: CategoryLocal,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "productCategoryId",
        entity = ProductLocal::class,
    )
    val products: List<ProductLocal>,
)