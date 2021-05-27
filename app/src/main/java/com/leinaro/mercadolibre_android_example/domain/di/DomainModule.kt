package com.leinaro.mercadolibre_android_example.domain.di

import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.domain.repository.Repository
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoryDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoryInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(repository: Repository): CategoryRepository

    @Singleton
    @Binds
    abstract fun bindGetCategoryInteractor(interactor: GetCategoryDomainInteractor): GetCategoryInteractor

}