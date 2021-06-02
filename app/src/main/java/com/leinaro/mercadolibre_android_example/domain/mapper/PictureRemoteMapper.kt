package com.leinaro.mercadolibre_android_example.domain.mapper

import com.leinaro.mercadolibre_android_example.datasource.model.PictureLocal
import com.leinaro.mercadolibre_android_example.datasource.model.PicturesRemote
import com.leinaro.mercadolibre_android_example.domain.common.Mapper
import javax.inject.Inject

class PictureRemoteMapper @Inject constructor() : Mapper<PicturesRemote, PictureLocal> {
    override fun map(input: PicturesRemote): PictureLocal {
        return PictureLocal(
            id = input.id,
            url = input.url,
            pictureProductId = input.productId.orEmpty()
        )
    }

}
