package com.leinaro.mercadolibre_android_example.domain.repository

interface ProductRepository {
    suspend fun getProducts()
}
