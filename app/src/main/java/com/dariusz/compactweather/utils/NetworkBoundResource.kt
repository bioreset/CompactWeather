package com.dariusz.compactweather.utils

import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object NetworkBoundResource {

    suspend fun <T, R> networkBoundResource(
        dataFromNetwork: T,
        insertDataFromNetworkToDB: suspend (T) -> Unit,
        selectFetchedData: R
    ): Flow<DataState<R>> {
        insertDataFromNetworkToDB.invoke(dataFromNetwork)
        return flow {
            try {
                emit(DataState.Success(selectFetchedData))
            } catch (exception: Exception) {
                emit(DataState.Error(exception))
            }
        }
    }

}