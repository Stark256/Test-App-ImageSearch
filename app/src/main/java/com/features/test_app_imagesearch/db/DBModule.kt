package com.features.test_app_imagesearch.db

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Provider
import javax.inject.Singleton

@Module
class DBModule() {

    @Singleton
    @Provides
    fun provideRealmImageRepo(realm: Realm) : RealmImageRepo {
        return RealmImageRepo(realm)
    }
}