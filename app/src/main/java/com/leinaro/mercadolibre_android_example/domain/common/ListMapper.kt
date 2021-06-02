package com.leinaro.mercadolibre_android_example.domain.common

import android.util.Log

// Non-nullable to Non-nullable
interface ListMapper<I, O> : Mapper<List<I>, List<O>>

class ListMapperImpl<I, O>(
    private val mapper: Mapper<I, O>,
) : ListMapper<I, O> {
    override fun map(input: List<I>): List<O> {
        return input.mapNotNull {
            try {
                mapper.map(it)
            } catch (e: NullPointerException) {
                Log.w(mapper::class.simpleName,
                    "Object ignored $it")
                Log.e(mapper::class.simpleName,
                    "Mapping could not be completed for object $it")

                e.printStackTrace()
                null
            }
        }
    }
}