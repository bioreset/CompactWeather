package com.dariusz.compactweather.di

import android.content.Context
import com.dariusz.compactweather.data.local.sensors.CurrentLocationCheckImpl
import com.dariusz.compactweather.data.local.sensors.GPSStateCheckImpl
import com.dariusz.compactweather.data.local.sensors.NetworkStateCheckImpl
import com.dariusz.compactweather.data.local.sensors.PermissionsCheckImpl
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiServiceImpl
import com.dariusz.compactweather.di.DatabaseModule.buildDatabase
import com.dariusz.compactweather.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideSavedCityRepository(@ApplicationContext context: Context): SavedCityRepository =
        SavedCityRepositoryImpl(
            CompactWeatherApiServiceImpl(),
            buildDatabase(context).savedCitiesDao()
        )

    @Provides
    fun provideCurrentConditionsRepository(@ApplicationContext context: Context): CurrentConditionsRepository =
        CurrentConditionsRepositoryImpl(
            CompactWeatherApiServiceImpl(),
            buildDatabase(context).currentConditionsDao()
        )

    @Provides
    fun provideDailyForecastRepository(@ApplicationContext context: Context): DailyForecastRepository =
        DailyForecastRepositoryImpl(
            CompactWeatherApiServiceImpl(),
            buildDatabase(context).dailyForecastDao()
        )

    @Provides
    fun provideHourlyForecastRepository(@ApplicationContext context: Context): HourlyForecastRepository =
        HourlyForecastRepositoryImpl(
            CompactWeatherApiServiceImpl(),
            buildDatabase(context).hourlyForecastDao()
        )

    @Provides
    @ExperimentalCoroutinesApi
    fun provideCurrentLocationRepository(@ApplicationContext context: Context): CurrentLocationRepository =
        CurrentLocationRepositoryImpl(
            CurrentLocationCheckImpl(context)
        )

    @ExperimentalCoroutinesApi
    @Provides
    fun provideRequirementsRepository(@ApplicationContext context: Context): RequirementsRepository =
        RequirementsRepositoryImpl(
            GPSStateCheckImpl(context),
            NetworkStateCheckImpl(context),
            PermissionsCheckImpl(context)
        )


}