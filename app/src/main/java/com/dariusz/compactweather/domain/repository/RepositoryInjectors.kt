package com.dariusz.compactweather.domain.repository

import android.content.Context
import com.dariusz.compactweather.di.DatabaseModule.provideCurrentConditionsDAO
import com.dariusz.compactweather.di.DatabaseModule.provideDailyForecastDAO
import com.dariusz.compactweather.di.DatabaseModule.provideHourlyForecastDAO
import com.dariusz.compactweather.di.NetworkModule.provideRetrofitService

object RepositoryInjectors {

    fun getAutoCompleteRepository(): AutoCompleteRepository =
        AutoCompleteRepository(
            provideRetrofitService()
        )

    fun getCurrentConditionsRepository(context: Context): CurrentConditionsRepository =
        CurrentConditionsRepository(
            provideRetrofitService(),
            provideCurrentConditionsDAO(context)
        )

    fun getDailyForecastRepository(context: Context): DailyForecastRepository =
        DailyForecastRepository(
            provideRetrofitService(),
            provideDailyForecastDAO(context)
        )

    fun getHourlyForecastRepository(context: Context): HourlyForecastRepository =
        HourlyForecastRepository(
            provideRetrofitService(),
            provideHourlyForecastDAO(context)
        )

}