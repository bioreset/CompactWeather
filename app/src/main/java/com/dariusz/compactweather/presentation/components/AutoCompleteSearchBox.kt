package com.dariusz.compactweather.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dariusz.compactweather.domain.model.AutoComplete

@Composable
fun AutoCompleteSearchBox(
    items: List<AutoComplete>,
    itemContent: @Composable (AutoComplete) -> Unit,
    content: @Composable () -> Unit,
    action: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items) { item ->
                Box(modifier = Modifier.clickable { action.invoke(item.key) }) {
                    itemContent(item)
                }
            }
        }
    }
}

@Composable
fun OneAutoCompleteItem(
    item: AutoComplete,
    action: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = item.localizedName)
        Text(text = item.country.id + ", " + item.administrativeArea.localizedName)
        action.invoke(item.key)
    }
}