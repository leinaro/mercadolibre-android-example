package com.leinaro.mercadolibre_android_example.domain.di

import com.leinaro.mercadolibre_android_example.datasource.local.DataBaseClient
import com.leinaro.mercadolibre_android_example.datasource.model.*
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
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
    fun provideRepository(
        mercadoLibreServices: MercadoLibreServices,
        mercadoLibreDataBase: DataBaseClient,
        picturesRemoteMapper: Mapper<PicturesRemote, PictureLocal>,
        productRemoteMapper: Mapper<ProductRemote, ProductLocal>,
        productLocalMapper: Mapper<ProductLocal, Product>,
        categoryRemoteMapper: Mapper<CategoryRemote, CategoryLocal>,
        categoryLocalMapper: Mapper<CategoryLocal, Category>,
        categoryWithProductsMapper: Mapper<CategoryWithProducts, Category>,
        productWithPicturesMapper: Mapper<ProductWithPictures, Product>,
    ): Repository =
        Repository(
            mercadoLibreServices,
            mercadoLibreDataBase,
            picturesRemoteMapper,
            productRemoteMapper,
            productLocalMapper,
            categoryRemoteMapper,
            categoryLocalMapper,
            categoryWithProductsMapper,
            productWithPicturesMapper,
        )
}