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

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun buildDatabase(@ApplicationContext context: Context): CompactWeatherDB {
        return Room.databaseBuilder(context, CompactWeatherDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSavedCitiesDAO(@ApplicationContext context: Context): SavedCityDao {
        return buildDatabase(context).savedCitiesDao()
    }

    @Provides
    fun provideCurrentConditionsDAO(@ApplicationContext context: Context): CurrentConditionsDao {
        return buildDatabase(context).currentConditionsDao()
    }

    @Provides
    fun provideDailyForecastDAO(@ApplicationContext context: Context): DailyForecastDao {
        return buildDatabase(context).dailyForecastDao()
    }

    @Provides
    fun provideHourlyForecastDAO(@ApplicationContext context: Context): HourlyForecastDao {
        return buildDatabase(context).hourlyForecastDao()
    }

}