package com.dariusz.compactweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.compactweather.domain.model.SavedCity

@Dao
interface SavedCityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedCity: SavedCity)

    @Query("DELETE FROM savedcities where cityID=:providedCityID;")
    suspend fun deleteSavedCityByID(providedCityID: Int)

    @Query("SELECT cityName FROM savedcities where cityID=:providedCityID;")
    suspend fun checkIfCityAlreadyExists(providedCityID: Int): String

    @Query("SELECT COUNT() FROM savedcities")
    suspend fun countRecordsInTable(): Int

    @Query("SELECT * FROM savedcities;")
    suspend fun getAllSavedCities(): List<SavedCity>

}