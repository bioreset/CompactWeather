package com.dariusz.compactweather.presentation.components.common

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableContents(
    initialContent: @Composable () -> Unit,
    fullContent: @Composable () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    val durationInMs = 125

    Surface(modifier = Modifier
        .clickable { expanded = !expanded }
        .fillMaxWidth()
        .padding(if (!expanded) 0.dp else 5.dp)
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationInMs, durationInMs)) with
                        fadeOut(animationSpec = tween(durationInMs)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height) at durationInMs
                                    durationMillis = 350
                                }
                            } else {
                                keyframes {
                                    IntSize(initialSize.width, targetSize.height) at durationInMs
                                    durationMillis = 350
                                }
                            }
                        }
            }
        ) { expanded ->
            if (expanded) fullContent() else initialContent()
        }
    }
}