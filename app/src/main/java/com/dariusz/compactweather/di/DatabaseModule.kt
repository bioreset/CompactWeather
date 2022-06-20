package com.dariusz.compactweather.di

import android.content.Context
import androidx.room.Room
import com.dariusz.compactweather.data.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.local.db.dao.SavedCityDao
import com.dariusz.compactweather.data.local.db.init.CompactWeatherDB
import com.dariusz.compactweather.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun buildDatabase(@ApplicationContext context: Context): CompactWeatherDB {
        return Room.databaseBuilder(context, CompactWeatherDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCurrentConditionsDao(compactWeatherDB: CompactWeatherDB): CurrentConditionsDao {
        return compactWeatherDB.currentConditionsDao()
    }

    @Provides
    @Singleton
    fun provideDailyForecastDao(compactWeatherDB: CompactWeatherDB): DailyForecastDao {
        return compactWeatherDB.dailyForecastDao()
    }

    @Provides
    @Singleton
    fun provideHourlyForecastDao(compactWeatherDB: CompactWeatherDB): HourlyForecastDao {
        return compactWeatherDB.hourlyForecastDao()
    }

    @Provides
    @Singleton
    fun provideSavedCityDao(compactWeatherDB: CompactWeatherDB): SavedCityDao {
        return compactWeatherDB.savedCitiesDao()
    }
}