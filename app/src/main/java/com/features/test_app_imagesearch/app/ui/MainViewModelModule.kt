package com.features.test_app_imagesearch.app.ui

import com.features.test_app_imagesearch.api.ApiService
import dagger.Module
import dagger.Provides

@Module
class MainViewModelModule {

    @Provides
    fun providesMainViewModelFactory(apiService: ApiService) : MainViewModelFactory {
        return  MainViewModelFactory(apiService)
    }
}