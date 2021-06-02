package com.leinaro.mercadolibre_android_example.datasource.local

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataBaseClient @Inject constructor(@ApplicationContext applicationContext: Context) {
    val db = Room.databaseBuilder(
        applicationContext,
        AppDataBase::class.java,
        "mercadolibre-database"
    ).build()
}