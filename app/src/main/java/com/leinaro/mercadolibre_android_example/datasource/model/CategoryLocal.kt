package com.leinaro.mercadolibre_android_example.datasource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryLocal(
    @PrimaryKey val categoryId: String,
    @ColumnInfo(name = "category_name")val name: String,
)

/*
{"id":"MCO1747","name":"Accesorios para Vehículos"}
*/