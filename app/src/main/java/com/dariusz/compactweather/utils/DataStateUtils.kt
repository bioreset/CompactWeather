package com.dariusz.compactweather.utils

import androidx.compose.runtime.Composable
import com.dariusz.compactweather.domain.model.DataState
import com.dariusz.compactweather.presentation.components.common.CenteredText
import com.dariusz.compactweather.presentation.components.common.LoadingComponent

object DataStateUtils {

    @Composable
    fun <T> ManageDataStateOnScreen(
        input: DataState<T>,
        content: @Composable (T) -> Unit,
    ) {
        when (input) {
            is DataState.Loading -> {
                LoadingComponent()
            }
            is DataState.Success -> {
                content.invoke(input.data)
            }
            is DataState.Error -> {
                CenteredText("Error: ${input.exception}")
            }
            is DataState.Idle -> {
                //default option; do nothing
            }
        }
    }

}