package com.features.test_app_imagesearch.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.features.test_app_imagesearch.api.ApiService
import com.features.test_app_imagesearch.api.models.ImagesResponse
import com.features.test_app_imagesearch.db.RealmImageRepo
import com.features.test_app_imagesearch.db.models.ImageDBModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api: ApiService, private val db: RealmImageRepo) : ViewModel() {

    val savedImages = MutableLiveData<ArrayList<ImageDBModel>>()
    var addNewImage = MutableLiveData<ImageDBModel>()
    private var imageDBModel: ImageDBModel? = null

    fun saveImage(byteArray: ByteArray) {
        imageDBModel?.let {
            db.saveImage(byteArray, imageDBModel!!)
        }
    }

    fun searchImage(query: String?, error: () -> Unit) {
        if(!query.isNullOrBlank()) {
            api.searchImage(query).enqueue(object : Callback<ImagesResponse> {
                override fun onFailure(call: Call<ImagesResponse>, t: Throwable) {
                    error.invoke()
                }

                override fun onResponse(
                    call: Call<ImagesResponse>,
                    response: Response<ImagesResponse>
                ) {
                    response.body()?.let { imagesResponse ->
                        val randomImage = imagesResponse.image_results.random()

                        db.createObject { imageDB ->
                            imageDBModel = imageDB
                            imageDBModel!!.imageName = query
                            imageDBModel!!.imageLink = randomImage.sourceUrl
                            addNewImage.value = imageDBModel
                        }

                    }
                }
            })
        } else {
            error.invoke()
        }
    }

    fun loadAllImages() {
        db.getAllImages {
            val arr = ArrayList<ImageDBModel>()
            arr.addAll(it)
            arr.sortByDescending { it.id }
            savedImages.value = arr
        }
    }
}