package com.leinaro.mercadolibre_android_example.di

import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.repository.ProductRepository
import com.leinaro.mercadolibre_android_example.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideProductRepository(mercadoLibreServices: MercadoLibreServices): ProductRepository =
        Repository(mercadoLibreServices)
}