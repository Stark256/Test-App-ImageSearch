package com.features.test_app_imagesearch.app

import android.app.Application
import com.features.test_app_imagesearch.R
import com.features.test_app_imagesearch.api.ApiModule
import com.features.test_app_imagesearch.db.DBModule
import io.realm.Realm
import io.realm.RealmConfiguration

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())

        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule(getString(R.string.base_url), getString(R.string.api_key)))
            .appModule(AppModule(this))
            .dBModule(DBModule())
            .build()

        appComponent.inject(this)
    }
}