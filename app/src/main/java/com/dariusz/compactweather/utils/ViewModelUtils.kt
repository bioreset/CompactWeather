package com.dariusz.compactweather.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.compactweather.domain.model.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

object ViewModelUtils {

    @Suppress("UNCHECKED_CAST")
    @Composable
    inline fun <reified VM : ViewModel> composeViewModel(crossinline viewModel: () -> VM): VM =
        viewModel(factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModel.invoke() as T
            }
        })


    fun <T> manageResult(
        mutableInput: MutableStateFlow<DataState<T>>,
        dataFromAction: T
    ) = mutableInput.getResultOfGenericResponse(dataFromAction)

    private fun <T> MutableStateFlow<DataState<T>>.getResultOfGenericResponse(
        data: T
    ) {
        value = DataState.Loading
        value = try {
            DataState.Success(data)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }


    @InternalCoroutinesApi
    suspend fun <T> Flow<T>.collectLatestState(
        mutableInput: MutableStateFlow<DataState<T>>
    ) = collectLatest {
        mutableInput.value = asResultState(it).value
    }

    private fun <T> asResultState(data: T): MutableStateFlow<DataState<T>> {
        return MutableStateFlow(DataState.Success(data))
    }


    private val ViewModel.ioTask
        get() = viewModelScope + Dispatchers.IO

    fun ViewModel.launchVMTask(
        action: suspend () -> Unit
    ) = ioTask.launch {
        action.invoke()
    }


}