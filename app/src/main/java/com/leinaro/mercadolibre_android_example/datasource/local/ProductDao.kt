package com.leinaro.mercadolibre_android_example.datasource.local

import androidx.room.*
import com.leinaro.mercadolibre_android_example.datasource.model.PictureLocal
import com.leinaro.mercadolibre_android_example.datasource.model.ProductLocal
import com.leinaro.mercadolibre_android_example.datasource.model.ProductWithPictures

@Dao
interface ProductDao {
    @Transaction
    @Query("SELECT * FROM productlocal WHERE productId = :productId")
    fun getProduct(productId: String): ProductWithPictures

    @Query("SELECT * FROM productlocal WHERE productCategoryId = :categoryId")
    fun getProductsByCategory(categoryId: String): List<ProductLocal>

    @Query("SELECT * FROM productlocal")
    fun getAllProducts(): List<ProductLocal>

    @Query("SELECT * FROM picturelocal")
    fun getAllPictures(): List<PictureLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productLocal: ProductLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(productsLocal: List<ProductLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictures(pictures: List<PictureLocal>)


}
