package com.leinaro.mercadolibre_android_example.domain.repository

import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.datasource.remote.MercadoLibreServices
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import javax.inject.Inject

class Repository @Inject constructor(
    private val mercadolibreServices: MercadoLibreServices,
    //  private val productDataMapper: Mapper<ProductRemote, Product>,

) : ProductRepository, CategoryRepository {
    override suspend fun getProducts() {

    }

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            Result.Success(mercadolibreServices.getCategories()
                .map { categoryApi ->
                    val products =
                        mercadolibreServices.getProductsByCategory(
                            categoryName = categoryApi.id,
                            limit = 5,
                        ).results
                            .map { productAPI ->
                                Product(
                                    id = productAPI.id,
                                    name = productAPI.title,
                                    price = productAPI.price.toString(),
                                    image = productAPI.thumbnail
                                )
                            }
                    val category = Category(
                        id = categoryApi.id,
                        name = categoryApi.name,
                        products = products
                    )
                    category
                }
            )
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

    private fun mapCategories(categories: List<Category>) {

    }

    /*  private fun mapProducts(networkProductList: List<ProductRemote>): List<Product> {
          return networkProductList.map {
              productDataMapper.map(it)
          }
      }*/
}