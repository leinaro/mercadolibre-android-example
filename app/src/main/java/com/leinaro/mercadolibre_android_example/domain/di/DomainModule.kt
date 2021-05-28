package com.leinaro.mercadolibre_android_example.domain.di

import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.domain.mapper.CategoryDataMapper
import com.leinaro.mercadolibre_android_example.domain.mapper.ProductDataMapper
import com.leinaro.mercadolibre_android_example.domain.model.CategoryRemote
import com.leinaro.mercadolibre_android_example.domain.model.ProductRemote
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.domain.repository.Repository
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoriesDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoriesInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category.GetProductByCategoryDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category.GetProductByCategoryInteractor
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
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
    abstract fun bindGetCategoryInteractor(interactor: GetCategoriesDomainInteractor): GetCategoriesInteractor

    @Singleton
    @Binds
    abstract fun bindGetProductByCategoryInteractor(interactor: GetProductByCategoryDomainInteractor): GetProductByCategoryInteractor

    @Singleton
    @Binds
    abstract fun bindProductDataMapper(productDataMapper: ProductDataMapper): Mapper<ProductRemote, Product>

    @Singleton
    @Binds
    abstract fun bindCategoryDataMapper(categoryDataMapper: CategoryDataMapper): Mapper<CategoryRemote, Category>

}