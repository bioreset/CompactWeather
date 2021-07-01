package com.dariusz.compactweather.di

import android.content.Context
import com.dariusz.compactweather.di.DatabaseModule.provideCurrentConditionsDAO
import com.dariusz.compactweather.di.DatabaseModule.provideDailyForecastDAO
import com.dariusz.compactweather.di.DatabaseModule.provideHourlyForecastDAO
import com.dariusz.compactweather.di.DatabaseModule.provideSavedCitiesDAO
import com.dariusz.compactweather.di.NetworkModule.provideRetrofitService
import com.dariusz.compactweather.domain.repository.CurrentConditionsRepository
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun getSavedCityRepository(@ApplicationContext context: Context): SavedCityRepository =
        SavedCityRepository(
            provideRetrofitService(),
            provideSavedCitiesDAO(context)
        )


    @Provides
    fun getCurrentConditionsRepository(@ApplicationContext context: Context): CurrentConditionsRepository =
        CurrentConditionsRepository(
            provideRetrofitService(),
            provideCurrentConditionsDAO(context)
        )

    @Provides
    fun getDailyForecastRepository(@ApplicationContext context: Context): DailyForecastRepository =
        DailyForecastRepository(
            provideRetrofitService(),
            provideDailyForecastDAO(context)
        )

    @Provides
    fun getHourlyForecastRepository(@ApplicationContext context: Context): HourlyForecastRepository =
        HourlyForecastRepository(
            provideRetrofitService(),
            provideHourlyForecastDAO(context)
        )
}