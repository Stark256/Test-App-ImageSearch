package com.features.test_app_imagesearch.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.features.test_app_imagesearch.api.ApiService
import com.features.test_app_imagesearch.db.RealmImageRepo
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val api: ApiService, private val db: RealmImageRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api, db) as T
    }
}