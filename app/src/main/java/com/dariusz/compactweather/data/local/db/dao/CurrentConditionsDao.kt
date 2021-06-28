package com.dariusz.compactweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.domain.model.CurrentConditions

@Dao
interface CurrentConditionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currentConditions: CurrentConditions)

    @Query("DELETE FROM currentconditions;")
    suspend fun deleteAllCurrentConditions()

    @Query("SELECT * FROM currentconditions;")
    suspend fun getAllCurrentConditions(): CurrentConditions

}