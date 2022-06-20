package com.dariusz.compactweather.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.dariusz.compactweather.domain.model.Result
import com.dariusz.compactweather.presentation.components.common.CenteredText
import com.dariusz.compactweather.presentation.components.common.LoadingComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

object ResultUtils {

    fun <T> Flow<T>.asResult(
        coroutineScope: CoroutineScope
    ): StateFlow<Result<T>> {
        return this
            .onStart {
                delay(1000)
            }
            .map<T, Result<T>> {
                Result.Success(it)
            }
            .catchAndEmit {
                Result.Error(it)
            }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Loading
            )
    }

    @Composable
    fun <T> State<Result<T>>.showOnScreen(content: @Composable (T) -> Unit) = when (value) {
        is Result.Success<T> -> {
            content((value as Result.Success<T>).data)
        }
        is Result.Error -> {
            CenteredText("Error: ${(value as Result.Error).throwable}")
        }
        is Result.Loading -> {
            LoadingComponent()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private inline fun <T> Flow<T>.catchAndEmit(crossinline action: (Throwable) -> Unit): Flow<T> =
        this.flatMapLatest {
            flow { emit(it) }
                .catch {
                    action(it)
                }
        }

}