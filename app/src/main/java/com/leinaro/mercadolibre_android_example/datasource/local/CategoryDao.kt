package com.leinaro.mercadolibre_android_example.datasource.local

import androidx.room.*
import com.leinaro.mercadolibre_android_example.datasource.model.CategoryLocal
import com.leinaro.mercadolibre_android_example.datasource.model.CategoryWithProducts

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categorylocal WHERE categoryId = :categoryId")
    fun getCategoryById(categoryId: String): CategoryLocal

    @Transaction
    @Query("SELECT * FROM categorylocal")
    fun getCategoryWithProducts(): List<CategoryWithProducts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(categoriesLocal: List<CategoryLocal>)
}