package com.dariusz.compactweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.domain.model.HourlyForecast

@Dao
interface HourlyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hourlyForecastJsonList: List<HourlyForecast>)

    @Query("DELETE FROM hourlyforecast;")
    suspend fun deleteAllHourlyForecasts()

    @Query("SELECT * FROM hourlyforecast;")
    suspend fun getAllHourlyForecasts(): List<HourlyForecast>

}