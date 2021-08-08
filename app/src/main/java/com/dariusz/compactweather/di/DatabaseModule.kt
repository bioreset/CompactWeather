package com.dariusz.compactweather.di

import android.content.Context
import androidx.room.Room
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

}