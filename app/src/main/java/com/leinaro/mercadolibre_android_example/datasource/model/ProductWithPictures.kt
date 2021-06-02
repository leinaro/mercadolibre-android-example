package com.leinaro.mercadolibre_android_example.datasource.model

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithPictures(
    @Embedded val product: ProductLocal,
    @Relation(
        parentColumn = "productId",
        entityColumn = "pictureProductId",
        entity = PictureLocal::class,
    )
    val pictures: List<PictureLocal>,
)