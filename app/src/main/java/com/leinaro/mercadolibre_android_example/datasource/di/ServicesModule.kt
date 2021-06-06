package com.leinaro.mercadolibre_android_example.datasource.di

import android.content.Context
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreClient
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    fun provideMercadoLibreServices(
        @ApplicationContext context: Context,
    ): MercadoLibreServices = MercadoLibreClient(context).services

}