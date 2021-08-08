package com.dariusz.compactweather.data.local.db.init

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariusz.compactweather.data.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.local.db.dao.SavedCityDao
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.domain.model.DailyForecast
import com.dariusz.compactweather.domain.model.HourlyForecast
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.utils.Constants.DB_VERSION

@Database(
    entities = [CurrentConditions::class, DailyForecast::class, HourlyForecast::class, SavedCity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class CompactWeatherDB : RoomDatabase() {

    abstract fun savedCitiesDao(): SavedCityDao
    abstract fun currentConditionsDao(): CurrentConditionsDao
    abstract fun dailyForecastDao(): DailyForecastDao
    abstract fun hourlyForecastDao(): HourlyForecastDao

}