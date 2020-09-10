package com.features.test_app_imagesearch.app

import android.app.Application
import com.features.test_app_imagesearch.api.ApiModule
import com.features.test_app_imagesearch.api.ApiService
import com.features.test_app_imagesearch.app.ui.MainActivity
import com.features.test_app_imagesearch.app.ui.MainViewModelModule
import com.features.test_app_imagesearch.db.DBModule
import com.features.test_app_imagesearch.db.RealmImageRepo
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    ApiModule::class,
    DBModule::class,
    MainViewModelModule::class
))
interface AppComponent {

    fun application(): Application
    fun apiService() : ApiService
    fun db() : RealmImageRepo

    fun inject(mainActivity: MainActivity)
    fun inject(app: Application)
}