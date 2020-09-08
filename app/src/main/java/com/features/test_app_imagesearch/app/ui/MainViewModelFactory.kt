package com.features.test_app_imagesearch.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.features.test_app_imagesearch.api.ApiService
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val api: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }
}