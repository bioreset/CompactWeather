package com.dariusz.compactweather.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import javax.inject.Inject

class SplashScreenViewModelFactory
@Inject
constructor(
    private val savedCityRepository: SavedCityRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashScreenViewModel(
            savedCityRepository
        ) as T
    }
}