package com.dariusz.compactweather.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LeftRightText(left: String, right: String){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
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

