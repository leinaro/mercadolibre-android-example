package com.leinaro.mercadolibre_android_example.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PictureLocal(
    @PrimaryKey val id: String,
    val url: String,
    val pictureProductId: String,
)

