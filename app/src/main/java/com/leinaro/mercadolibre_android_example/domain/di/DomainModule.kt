package com.leinaro.mercadolibre_android_example.domain.di

import com.leinaro.mercadolibre_android_example.datasource.model.*
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import com.leinaro.mercadolibre_android_example.domain.mapper.*
import com.leinaro.mercadolibre_android_example.domain.repository.CategoryRepository
import com.leinaro.mercadolibre_android_example.domain.repository.ProductRepository
import com.leinaro.mercadolibre_android_example.domain.repository.Repository
import com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories.GetCategoriesDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_all_categories.GetCategoriesInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoryDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoryInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product.GetProductDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product.GetProductInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category.GetProductByCategoryDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_category.GetProductByCategoryInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_query.GetProductByQueryDomainInteractor
import com.leinaro.mercadolibre_android_example.domain.usecase.get_product_by_query.GetProductByQueryInteractor
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
    abstract fun bindProductRepository(repository: Repository): ProductRepository

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(repository: Repository): CategoryRepository

    // region Category Interactors
    @Singleton
    @Binds
    abstract fun bindGetCategoryInteractor(interactor: GetCategoryDomainInteractor): GetCategoryInteractor

    @Singleton
    @Binds
    abstract fun bindGetCategoriesInteractor(interactor: GetCategoriesDomainInteractor): GetCategoriesInteractor
    // endregion

    // region Product Interactors
    @Singleton
    @Binds
    abstract fun bindGetProductInteractor(interactor: GetProductDomainInteractor): GetProductInteractor

    @Singleton
    @Binds
    abstract fun bindGetProductByCategoryInteractor(interactor: GetProductByCategoryDomainInteractor): GetProductByCategoryInteractor

    @Singleton
    @Binds
    abstract fun bindGetProductByQueryInteractor(interactor: GetProductByQueryDomainInteractor): GetProductByQueryInteractor
    // endregion

    @Singleton
    @Binds
    abstract fun bindProductRemoteMapper(productRemoteMapper: ProductRemoteMapper): Mapper<ProductRemote, ProductLocal>

    @Singleton
    @Binds
    abstract fun bindProductLocalMapper(productLocalMapper: ProductLocalMapper): Mapper<ProductLocal, Product>

    // region Category Mappers
    @Singleton
    @Binds
    abstract fun bindCategoryRemoteMapper(categoryRemoteMapper: CategoryRemoteMapper): Mapper<CategoryRemote, CategoryLocal>

    @Singleton
    @Binds
    abstract fun bindCategoryLocalMapper(categoryLocalMapper: CategoryLocalMapper): Mapper<CategoryLocal, Category>

    @Singleton
    @Binds
    abstract fun bindCategoryWithProductsMapper(categoryLocalMapper: CategoryWithProductsMapper): Mapper<CategoryWithProducts, Category>

    @Singleton
    @Binds
    abstract fun bindProductWithPicturesMapper(productWithPicturesMapper: ProductWithPicturesMapper): Mapper<ProductWithPictures, Product>
    // endregion

    @Singleton
    @Binds
    abstract fun bindPicturesMapper(pictureRemoteMapper: PictureRemoteMapper): Mapper<PicturesRemote, PictureLocal>

}