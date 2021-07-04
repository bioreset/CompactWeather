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

    @Query("DELETE FROM savedcities WHERE cityID=:providedCityID;")
    suspend fun deleteSavedCityByID(providedCityID: Int)

    @Query("SELECT COUNT(cityName) FROM savedcities WHERE cityID=:providedCityID;")
    suspend fun checkIfCityAlreadyExists(providedCityID: Int): Int

    @Query("SELECT * FROM savedcities;")
    suspend fun getAllSavedCities(): List<SavedCity>

}