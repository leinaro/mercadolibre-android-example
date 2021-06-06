package com.leinaro.mercadolibre_android_example.datasource.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


private const val BASE_URL = "https://api.mercadolibre.com/"

@Singleton
class MercadoLibreClient @Inject constructor(@ApplicationContext private val context: Context) {
    //  @Inject
    //  lateinit var noConnectionInterceptor: NoConnectionInterceptor


    val services: MercadoLibreServices = getRetrofit().create(MercadoLibreServices::class.java)

    private fun getRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(Level.BASIC)

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(NoConnectionInterceptor(context))
            .addInterceptor(logging)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}