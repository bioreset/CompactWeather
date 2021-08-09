package com.dariusz.compactweather.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.dariusz.compactweather.domain.model.CurrentLocation
import com.dariusz.compactweather.domain.repository.SavedCityRepository
import com.dariusz.compactweather.utils.ViewModelUtils.launchVMTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel
@Inject constructor(
    private val savedCityRepository: SavedCityRepository
) : ViewModel() {

    fun manageCities(location: CurrentLocation) = launchVMTask {
        savedCityRepository.manageCities(location)
    }

}