package com.features.test_app_imagesearch.app

import android.app.Application
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() : Application{
        return application
    }

    @Singleton
    @Provides
    fun provideRealm() : Realm {
        return Realm.getDefaultInstance()
    }
}