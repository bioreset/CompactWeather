package com.dariusz.compactweather.domain.model

import com.dariusz.compactweather.presentation.theme.ThemeColor
import com.dariusz.compactweather.presentation.theme.ThemeShapesFamily
import com.dariusz.compactweather.presentation.theme.ThemeTypography

data class AppTheme(
    val primaryColor: ThemeColor = ThemeColor.Blue,
    val secondaryColor: ThemeColor = ThemeColor.Yellow,
    val shapesFamily: ThemeShapesFamily = ThemeShapesFamily.Rounded,
    val mainTypography: ThemeTypography = ThemeTypography.Main
)