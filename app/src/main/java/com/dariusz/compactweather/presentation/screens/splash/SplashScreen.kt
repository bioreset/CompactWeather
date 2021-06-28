package com.dariusz.compactweather.presentation.screens.splash

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalView
import com.dariusz.compactweather.domain.model.AutoComplete
import com.dariusz.compactweather.presentation.components.AutoCompleteSearchBox
import com.dariusz.compactweather.presentation.components.OneAutoCompleteItem
import com.dariusz.compactweather.presentation.components.SearchBox

@Composable
fun SplashScreen() {

}

@Composable
fun FinalAutoCompleteBox(
    input: List<AutoComplete>,
    actionWithItem: (Int) -> Unit,
    actionAfterSelection: () -> Unit
) {
    AutoCompleteSearchBox(
        items = input,
        itemContent = { item ->
            OneAutoCompleteItem(item, actionWithItem)
        },
        content = {
            var value by remember { mutableStateOf("") }
            val view = LocalView.current
            SearchBox(
                value = value,
                label = "Type city",
                onDoneActionClick = {
                    actionAfterSelection.invoke()
                    view.clearFocus()
                },
                onClearClick = {
                    value = ""
                    view.clearFocus()
                },
                onValueChanged = { query ->
                    value = query
                }
            )
        }
    ) {

    }
}