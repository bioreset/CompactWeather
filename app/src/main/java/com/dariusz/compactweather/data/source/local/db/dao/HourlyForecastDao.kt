package com.dariusz.compactweather.data.source.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.model.HourlyForecast

interface HourlyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hourlyForecastList: List<HourlyForecast>)

    @Query("DELETE FROM hourlyforecasts;")
    suspend fun deleteAllHourlyForecasts()

    @Query("SELECT * FROM hourlyforecasts;")
    suspend fun getAllHourlyForecasts(): List<HourlyForecast>

}