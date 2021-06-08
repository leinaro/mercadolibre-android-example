package com.leinaro.mercadolibre_android_example.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductLocal(
    @PrimaryKey val productId: String,
    val productCategoryId: String?,
    @ColumnInfo(name = "product_title") val title: String,
    @ColumnInfo(name = "product_price") val price: String?,
    @ColumnInfo(name = "product_thumbnail") val thumbnail: String,
)

