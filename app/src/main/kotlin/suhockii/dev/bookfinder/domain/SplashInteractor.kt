package suhockii.dev.bookfinder.domain

import suhockii.dev.bookfinder.domain.repository.SettingsRepository
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    fun isDataLoaded() = settingsRepository.isDatabaseLoaded
}