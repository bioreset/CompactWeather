package com.dariusz.compactweather.data.source.local.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.model.CurrentConditions

interface CurrentConditionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currentConditions: CurrentConditions)

    @Query("DELETE FROM bookings;")
    suspend fun deleteAllBookings()

    @Query("SELECT * FROM bookings;")
    suspend fun getAllBookings(): CurrentConditions

}