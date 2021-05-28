package com.leinaro.mercadolibre_android_example.di

import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.domain.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.domain.model.ProductRemote
import com.leinaro.mercadolibre_android_example.domain.repository.ProductRepository
import com.leinaro.mercadolibre_android_example.domain.repository.Repository
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideProductRepository(
        mercadoLibreServices: MercadoLibreServices,
        productDataMapper: Mapper<ProductRemote, Product>,
        categoryDataMapper: Mapper<CategoryRemote, Category>,
    ): ProductRepository =
        Repository(mercadoLibreServices, productDataMapper, categoryDataMapper)
}