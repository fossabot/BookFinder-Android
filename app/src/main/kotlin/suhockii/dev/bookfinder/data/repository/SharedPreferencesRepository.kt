package suhockii.dev.bookfinder.data.repository

import android.content.SharedPreferences
import suhockii.dev.bookfinder.domain.repository.SettingsRepository
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {
    override var isDatabaseLoaded: Boolean
        get() = sharedPreferences.getBoolean(PREFERENCE_IS_DATABASE_LOADED, false)
        set(value) = sharedPreferences.edit().putBoolean(PREFERENCE_IS_DATABASE_LOADED, value).apply()

    companion object {
        const val PREFERENCE_IS_DATABASE_LOADED = "PREFERENCE_IS_DATABASE_LOADED"
    }
}