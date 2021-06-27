package com.dariusz.compactweather.di

import android.content.Context

import com.dariusz.compactweather.utils.PreferencesUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewComponent::class)
object DataStoreModule {

    @Provides
    fun provideDataStore(@ApplicationContext context: Context) =
        PreferencesUtils(context).dataStore

}