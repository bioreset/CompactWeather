package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.SavedCityDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class SavedCityRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val savedCityDao: SavedCityDao
) {

    suspend fun insertCity(location: CurrentLocation) = flow {
        val cityToSave =
            compactWeatherApiService.getCityKeyBasedOnLocation(location.latitude.toString() + "," + location.longitude.toString())
        try {
            if (savedCityDao.checkIfCityAlreadyExists(cityToSave.cityID) !== cityToSave.cityName) {
                savedCityDao.insert(cityToSave)
                emit(DataState.Success(true))
            } else {
                emit(DataState.Success(false))
            }
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }.shareIn(
        MainScope(),
        SharingStarted.Eagerly,
        1
    )

    suspend fun listSavedCitiesFromDB() = flow {
        try {
            val savedCitiesInDB = savedCityDao.getAllSavedCities()
            emit(
                DataState.Success(
                    savedCitiesInDB
                )
            )
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }.shareIn(
        MainScope(),
        SharingStarted.Eagerly,
        1
    )

    suspend fun countCitiesInDB() = flow {
        try {
            val countSavedCitiesInDB = savedCityDao.countRecordsInTable()
            emit(
                DataState.Success(
                    countSavedCitiesInDB
                )
            )
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }.shareIn(
        MainScope(),
        SharingStarted.Eagerly,
        1
    )
}