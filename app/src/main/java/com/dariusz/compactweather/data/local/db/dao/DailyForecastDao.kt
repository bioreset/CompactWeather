package com.dariusz.compactweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.domain.model.DailyForecast

@Dao
interface DailyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dailyForecastJsonList: List<DailyForecast>)

    @Query("DELETE FROM dailyforecast;")
    suspend fun deleteAllDailyForecasts()

    @Query("SELECT * FROM dailyforecast;")
    suspend fun getAllDailyForecasts(): List<DailyForecast>

}