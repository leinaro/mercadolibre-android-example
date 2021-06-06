package com.leinaro.mercadolibre_android_example.datasource.model

data class PicturesRemote(
    val id: String,
    val url: String,
    var productId: String? = null,
)