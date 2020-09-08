package com.features.test_app_imagesearch.app.ui

import androidx.lifecycle.ViewModel
import com.features.test_app_imagesearch.api.ApiService
import com.features.test_app_imagesearch.api.models.ImagesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val api: ApiService) : ViewModel() {



    fun searchImage(query: String?) {
        api.searchImage(query).enqueue(object : Callback<ImagesResponse>{
            override fun onFailure(call: Call<ImagesResponse>, t: Throwable) {
                t
            }

            override fun onResponse(call: Call<ImagesResponse>, response: Response<ImagesResponse>) {
                response
                // TODO get random image from image_result field and save it in realm db
            }
        })
    }
}