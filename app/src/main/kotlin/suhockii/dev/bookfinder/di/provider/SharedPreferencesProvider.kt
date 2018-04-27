package suhockii.dev.bookfinder.di.provider

import android.content.Context
import android.content.SharedPreferences
import suhockii.dev.bookfinder.di.qualifier.SharedPreferencesFileName
import javax.inject.Inject
import javax.inject.Provider

class SharedPreferencesProvider @Inject constructor(
    private val context: Context,
    @SharedPreferencesFileName private val sharedPreferencesFileName: String
) : Provider<SharedPreferences> {

    override fun get(): SharedPreferences =
        context.getSharedPreferences(
            sharedPreferencesFileName,
            Context.MODE_PRIVATE
        )
}