package com.dariusz.compactweather.domain.repository

import com.dariusz.compactweather.data.source.remote.api.CompactWeatherApiService
import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AutoCompleteRepository
@Inject constructor(
    private val compactWeatherApiService: CompactWeatherApiService
) {

    suspend fun getAutoComplete(q: String) = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            emit(DataState.Success(compactWeatherApiService.getAutoComplete(q)))
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
    }


}