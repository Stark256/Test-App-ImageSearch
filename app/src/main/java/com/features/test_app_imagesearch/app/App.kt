package com.features.test_app_imagesearch.app

import android.app.Application
import com.features.test_app_imagesearch.R
import com.features.test_app_imagesearch.api.ApiModule

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()


        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule(getString(R.string.base_url), getString(R.string.api_key)))
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}