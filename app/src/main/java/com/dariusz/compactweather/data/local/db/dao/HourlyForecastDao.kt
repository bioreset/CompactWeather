package com.dariusz.compactweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.domain.model.HourlyForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface HourlyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hourlyForecastJsonList: List<HourlyForecast>)

    @Query("DELETE FROM hourlyforecast;")
    suspend fun deleteAllHourlyForecasts()

    @Query("SELECT * FROM hourlyforecast;")
    fun getAllHourlyForecasts(): Flow<List<HourlyForecast>>

}