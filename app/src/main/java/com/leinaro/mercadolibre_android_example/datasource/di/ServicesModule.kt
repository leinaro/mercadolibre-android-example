package com.leinaro.mercadolibre_android_example.datasource.di

import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreClient
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    fun provideMercadoLibreServices(): MercadoLibreServices = MercadoLibreClient.services

}