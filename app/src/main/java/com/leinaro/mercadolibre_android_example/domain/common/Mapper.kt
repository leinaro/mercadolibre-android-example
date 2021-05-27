package com.leinaro.mercadolibre_android_example.domain.common

interface Mapper<I, O> {
    fun map(input: I): O
}