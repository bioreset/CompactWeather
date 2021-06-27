package com.dariusz.compactweather.data.source.local.db.init

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariusz.compactweather.data.source.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.source.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.source.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.model.CurrentConditions
import com.dariusz.compactweather.model.DailyForecast
import com.dariusz.compactweather.model.HourlyForecast
import com.dariusz.compactweather.utils.Constants.DB_VERSION

@Database(
    entities = [CurrentConditions::class, DailyForecast::class, HourlyForecast::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class CompactWeatherDB : RoomDatabase() {

    abstract fun currentConditionsDao() : CurrentConditionsDao
    abstract fun dailyForecastDao() : DailyForecastDao
    abstract fun hourlyForecastDao() : HourlyForecastDao

}