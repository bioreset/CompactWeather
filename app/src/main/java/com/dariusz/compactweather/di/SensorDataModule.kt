package com.dariusz.compactweather.di

import android.content.Context
import com.dariusz.compactweather.data.local.sensors.CurrentLocationCheck
import com.dariusz.compactweather.data.local.sensors.GPSStateCheck
import com.dariusz.compactweather.data.local.sensors.NetworkStateCheck
import com.dariusz.compactweather.data.local.sensors.PermissionsCheck
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
        CurrentLocationCheck(context)

    @Provides
    fun provideNetworkStateCheck(@ApplicationContext context: Context): NetworkStateCheck =
        NetworkStateCheck(context)

    @Provides
    fun provideGpsStateCheck(@ApplicationContext context: Context): GPSStateCheck =
        GPSStateCheck(context)

    @Provides
    fun providePermissionStateCheck(@ApplicationContext context: Context): PermissionsCheck =
        PermissionsCheck(context)
}