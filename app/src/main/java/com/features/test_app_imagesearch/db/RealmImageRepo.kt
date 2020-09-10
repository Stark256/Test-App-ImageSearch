package com.features.test_app_imagesearch.db

import com.features.test_app_imagesearch.db.models.ImageDBModel
import io.realm.Realm
import javax.inject.Inject

class RealmImageRepo @Inject constructor(private val realm: Realm) : ImageRepo {

    override fun getAllImages(result: (List<ImageDBModel>) -> Unit) {
        result.invoke(realm.where(ImageDBModel::class.java).findAll().sort("id").toList())
    }

    override fun saveImage(byteArray: ByteArray, imageDBModel: ImageDBModel) {
        realm.executeTransaction { r ->
            imageDBModel.image = byteArray
            r.copyToRealmOrUpdate(imageDBModel)
        }
    }

    override fun createObject(imageDBModel: (ImageDBModel) -> Unit) {
        realm.beginTransaction()
        imageDBModel.invoke(realm.createObject(ImageDBModel::class.java, getNextKey()))
        realm.commitTransaction()
    }

    override fun getNextKey(): Int {
        try{
            val maxId: Int? = realm.where(ImageDBModel::class.java).max("id")?.toInt()
            return if(maxId != null) (maxId + 1) else 0
        } catch (e: ArrayIndexOutOfBoundsException) {
            return 0
        }
    }

}