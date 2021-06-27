package com.dariusz.compactweather.data.source.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.model.DailyForecast

interface DailyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dailyForecast: List<DailyForecast>)

    @Query("DELETE FROM dailyforecasts;")
    suspend fun deleteAllDailyForecasts()

    @Query("SELECT * FROM dailyforecasts;")
    suspend fun getAllBookings(): List<DailyForecast>

}