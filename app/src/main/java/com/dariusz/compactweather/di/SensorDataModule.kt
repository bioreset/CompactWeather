package com.dariusz.compactweather.di

import android.content.Context
import com.dariusz.compactweather.data.local.sensors.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
@ExperimentalCoroutinesApi
object SensorDataModule {

    @Provides
    fun provideCurrentLocationCheck(@ApplicationContext context: Context): CurrentLocationCheck =
        CurrentLocationCheckImpl(context)

    @Provides
    fun provideNetworkStateCheck(@ApplicationContext context: Context): NetworkStateCheck =
        NetworkStateCheckImpl(context)

    @Provides
    fun provideGpsStateCheck(@ApplicationContext context: Context): GPSStateCheck =
        GPSStateCheckImpl(context)

    @Provides
    fun providePermissionStateCheck(@ApplicationContext context: Context): PermissionsCheck =
        PermissionsCheckImpl(context)
}