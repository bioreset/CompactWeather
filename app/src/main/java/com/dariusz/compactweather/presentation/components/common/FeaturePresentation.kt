package com.dariusz.compactweather.presentation.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dariusz.compactweather.domain.model.CurrentConditions
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography
import com.dariusz.compactweather.presentation.components.theme.getTypography

@Composable
fun CurrentConditions(input: CurrentConditions) {
    Text(
        text = "Current Conditions in your location: ",
        style = ThemeTypography.Main.getTypography().h1
    )
    LeftRightText(left = "", right = input.datetime)
    LeftRightText(left = "", right = input.weatherCondition)
    LeftRightText(left = "", right = input.temperature)
    LeftRightText(left = "", right = input.realFeelTemperature)
    LeftRightText(left = "", right = input.humidity.toString())
    LeftRightText(left = "", right = input.wind)
    LeftRightText(left = "", right = input.cloudCover.toString())
    LeftRightText(left = "", right = input.pressure)
    Text(text = "Updates as of ${input.datetime}")
}


@Composable
fun LeftRightText(left: String, right: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ConstraintLayout {
            val (text1, text2) = createRefs()
            Text(left, modifier = Modifier.constrainAs(text1) {
                centerVerticallyTo(parent)
                linkTo(parent.start, parent.end, bias = 0.2f)
            }
            )
            Text(right, modifier = Modifier.constrainAs(text2) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
                linkTo(parent.start, parent.end, bias = 0.8f)
            }
            )
        }
    }
}

