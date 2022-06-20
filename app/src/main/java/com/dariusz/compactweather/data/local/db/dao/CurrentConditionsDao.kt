package com.dariusz.compactweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.domain.model.CurrentConditions
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentConditionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currentConditions: CurrentConditions)

    @Query("DELETE FROM currentconditions;")
    suspend fun deleteAllCurrentConditions()

    @Query("SELECT * FROM currentconditions;")
    fun getAllCurrentConditions(): Flow<CurrentConditions>

}