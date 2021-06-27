package com.dariusz.compactweather.model

import com.dariusz.compactweather.ui.theme.ThemeColor
import com.dariusz.compactweather.ui.theme.ThemeShapesFamily
import com.dariusz.compactweather.ui.theme.ThemeTypography

data class AppTheme(
    val primaryColor: ThemeColor = ThemeColor.Blue,
    val secondaryColor: ThemeColor = ThemeColor.Yellow,
    val shapesFamily: ThemeShapesFamily = ThemeShapesFamily.Rounded,
    val mainTypography: ThemeTypography = ThemeTypography.Main
)