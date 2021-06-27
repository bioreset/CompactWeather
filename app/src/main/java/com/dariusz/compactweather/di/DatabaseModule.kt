package com.dariusz.compactweather.di

import android.content.Context
import androidx.room.Room
import com.dariusz.compactweather.data.source.local.db.dao.CurrentConditionsDao
import com.dariusz.compactweather.data.source.local.db.dao.DailyForecastDao
import com.dariusz.compactweather.data.source.local.db.dao.HourlyForecastDao
import com.dariusz.compactweather.data.source.local.db.init.CompactWeatherDB
import com.dariusz.compactweather.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object DatabaseModule {

    @Provides
    fun buildDatabase(@ApplicationContext context: Context): CompactWeatherDB {
        return Room.databaseBuilder(context, CompactWeatherDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
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