package com.leinaro.mercadolibre_android_example.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leinaro.mercadolibre_android_example.datasource.model.CategoryLocal
import com.leinaro.mercadolibre_android_example.datasource.model.PictureLocal
import com.leinaro.mercadolibre_android_example.datasource.model.ProductLocal

@Database(entities = [
    CategoryLocal::class,
    ProductLocal::class,
    PictureLocal::class,
],
    version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}