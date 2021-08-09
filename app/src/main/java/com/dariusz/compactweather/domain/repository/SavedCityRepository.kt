package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.local.db.dao.SavedCityDao
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.model.SavedCity
import com.dariusz.compactweather.utils.NetworkBoundResource.networkBoundResource
import javax.inject.Inject

interface SavedCityRepository {

    suspend fun manageCities(location: CurrentLocation): List<SavedCity>

    suspend fun getSavedCities(): List<SavedCity>
}

class SavedCityRepositoryImpl
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val savedCityDao: SavedCityDao
) : SavedCityRepository {

    override suspend fun manageCities(location: CurrentLocation): List<SavedCity> =
        networkBoundResource(
            dataFromNetwork = fetchCityBasedOnLocation(location),
            insertDataFromNetworkToDB = { insertCity(it) },
            selectFetchedData = getSavedCities()
        )

    private suspend fun fetchCityBasedOnLocation(location: CurrentLocation) =
        compactWeatherApiService.getCityKeyBasedOnLocation(location.latitude.toString() + "," + location.longitude.toString())

    private suspend fun insertCity(inputCity: SavedCity) = inputCity.let {
        if (!checkIfCityAlreadyExists(it))
            savedCityDao.insert(it)
    }

    private suspend fun checkIfCityAlreadyExists(inputCity: SavedCity) =
        savedCityDao.checkIfCityAlreadyExists(inputCity.cityID) > 0

    override suspend fun getSavedCities() =
        savedCityDao.getAllSavedCities()

}