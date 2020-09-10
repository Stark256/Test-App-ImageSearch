package com.features.test_app_imagesearch.db

import com.features.test_app_imagesearch.db.models.ImageDBModel

interface ImageRepo {

    fun getAllImages(result: (List<ImageDBModel>) -> Unit)
    fun createObject(imageDBModel: (ImageDBModel) -> Unit)
    fun saveImage(byteArray: ByteArray, imageDBModel: ImageDBModel)
    fun getNextKey() : Int

}