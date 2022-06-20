package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.SavedCityDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.SavedCity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SavedCityRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val savedCityDao: SavedCityDao
) {

    suspend fun manageCities(location: CurrentLocation): Flow<List<SavedCity>> {
        insertCity(fetchCityBasedOnLocation(location))
        return getSavedCities()
    }

    private suspend fun fetchCityBasedOnLocation(location: CurrentLocation) =
        compactWeatherApiService.getCityKeyBasedOnLocation(location.latitude.toString() + "," + location.longitude.toString())

    private suspend fun insertCity(inputCity: SavedCity) = inputCity.let {
        if (!checkIfCityAlreadyExists(it))
            savedCityDao.insert(it)
    }

    private suspend fun checkIfCityAlreadyExists(inputCity: SavedCity) =
        savedCityDao.checkIfCityAlreadyExists(inputCity.cityID) > 0

    fun getSavedCities(): Flow<List<SavedCity>> = savedCityDao.getAllSavedCities()

}