package dev.suhockii.bookfinder.di.module

import com.google.gson.Gson
import dev.suhockii.bookfinder.BuildConfig
import dev.suhockii.bookfinder.di.provider.ApiProvider
import dev.suhockii.bookfinder.di.provider.GsonProvider
import dev.suhockii.bookfinder.di.qualifier.GoogleDrivePath
import dev.suhockii.bookfinder.model.data.server.GoogleDriveApi
import dev.suhockii.bookfinder.model.interactor.GoogleDriveInteractor
import dev.suhockii.bookfinder.model.repository.GoogleDriveRepository
import toothpick.config.Module

class ServerModule : Module() {
    init {
        //Network
        bind(String::class.java).withName(GoogleDrivePath::class.java)
            .toInstance(BuildConfig.GOOGLE_DRIVE_URL)
        bind(Gson::class.java).toProvider(GsonProvider::class.java)
            .singletonInScope()
        bind(GoogleDriveApi::class.java).toProvider(ApiProvider::class.java)
            .singletonInScope()

        //Profile
        bind(GoogleDriveRepository::class.java)
        bind(GoogleDriveInteractor::class.java)
    }
}