package com.dariusz.compactweather.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import com.dariusz.compactweather.model.AutoComplete
import com.dariusz.compactweather.ui.components.AutoCompleteSearchBox
import com.dariusz.compactweather.ui.components.OneAutoCompleteItem
import com.dariusz.compactweather.ui.components.SearchBox

@Composable
fun SplashScreen(){

}

@Composable
fun FinalAutoCompleteBox(
    input: List<AutoComplete>,
    actionWithItem: (Int) -> Unit,
    actionAfterSelection: () -> Unit
){
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
    ){

    }
}