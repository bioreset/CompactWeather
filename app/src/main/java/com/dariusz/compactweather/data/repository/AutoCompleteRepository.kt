package com.dariusz.compactweather.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.model.AutoComplete
import javax.inject.Inject

class AutoCompleteRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService,
    private val dataStore: DataStore<Preferences>
) {

    suspend fun getAutoComplete(q: String): List<AutoComplete> =
        compactWeatherApiService.getAutoComplete(q)

}