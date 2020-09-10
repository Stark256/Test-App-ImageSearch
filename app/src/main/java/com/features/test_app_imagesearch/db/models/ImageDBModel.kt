package com.features.test_app_imagesearch.db.models

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

open class ImageDBModel : RealmObject() {
    @PrimaryKey
    @RealmField(name = "id")
    var id: Int = 0

    @RealmField(name = "imageName")
    lateinit var imageName: String

    @RealmField(name = "image")
    lateinit var image: ByteArray

    @Ignore
    var imageLink: String? = null
}