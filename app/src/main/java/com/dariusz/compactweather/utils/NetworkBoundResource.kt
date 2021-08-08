package com.dariusz.compactweather.utils

object NetworkBoundResource {

    suspend fun <T, R> networkBoundResource(
        dataFromNetwork: T,
        insertDataFromNetworkToDB: suspend (T) -> Unit,
        selectFetchedData: R
    ): R {
        insertDataFromNetworkToDB.invoke(dataFromNetwork)
        return selectFetchedData
    }
}

