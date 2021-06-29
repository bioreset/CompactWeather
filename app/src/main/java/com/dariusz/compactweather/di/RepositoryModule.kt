package com.dariusz.compactweather.di

import android.content.Context
import com.dariusz.compactweather.domain.repository.CurrentConditionsRepository
import com.dariusz.compactweather.domain.repository.DailyForecastRepository
import com.dariusz.compactweather.domain.repository.HourlyForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun getCurrentConditionsRepository(@ApplicationContext context: Context): CurrentConditionsRepository =
        CurrentConditionsRepository(
            NetworkModule.provideRetrofitService(),
            DatabaseModule.provideCurrentConditionsDAO(context)
        )

    @Provides
    @ViewModelScoped
    fun getDailyForecastRepository(@ApplicationContext context: Context): DailyForecastRepository =
        DailyForecastRepository(
            NetworkModule.provideRetrofitService(),
            DatabaseModule.provideDailyForecastDAO(context)
        )

    @Provides
    @ViewModelScoped
    fun getHourlyForecastRepository(@ApplicationContext context: Context): HourlyForecastRepository =
        HourlyForecastRepository(
            NetworkModule.provideRetrofitService(),
            DatabaseModule.provideHourlyForecastDAO(context)
        )
}