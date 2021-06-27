package com.dariusz.compactweather.utils

import android.content.Context
import com.dariusz.compactweather.data.repository.AutoCompleteRepository
import com.dariusz.compactweather.data.repository.CurrentConditionsRepository
import com.dariusz.compactweather.data.repository.DailyForecastRepository
import com.dariusz.compactweather.data.repository.HourlyForecastRepository
import com.dariusz.compactweather.di.DataStoreModule.provideDataStore
import com.dariusz.compactweather.di.NetworkModule.provideRetrofitService

object Injectors {

    fun getAutoCompleteRepository(context: Context): AutoCompleteRepository =
        AutoCompleteRepository(
            provideRetrofitService(),
        provideDataStore(context))

    fun getCurrentConditionsRepository(): CurrentConditionsRepository =
        CurrentConditionsRepository(
            provideRetrofitService())

    fun getDailyForecastRepository(): DailyForecastRepository =
        DailyForecastRepository(
            provideRetrofitService())

    fun getHourlyForecastRepository(): HourlyForecastRepository =
        HourlyForecastRepository(
            provideRetrofitService())

}