package com.dariusz.compactweather.domain.model

import com.dariusz.compactweather.presentation.components.theme.ThemeColor
import com.dariusz.compactweather.presentation.components.theme.ThemeShapesFamily
import com.dariusz.compactweather.presentation.components.theme.ThemeTypography

data class AppTheme(
    val primaryColor: ThemeColor = ThemeColor.Blue,
    val secondaryColor: ThemeColor = ThemeColor.Purple,
    val shapesFamily: ThemeShapesFamily = ThemeShapesFamily.Rounded,
    val mainTypography: ThemeTypography = ThemeTypography.Main
)