package com.leinaro.mercadolibre_android_example.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.mercadolibre.com/sites/MCO/"

object MercadoLibreClient {

    val services: MercadoLibreServices = getRetrofit().create(MercadoLibreServices::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}