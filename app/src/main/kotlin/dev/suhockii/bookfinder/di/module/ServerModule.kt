package dev.suhockii.bookfinder.di.module

import com.google.gson.Gson
import dev.suhockii.bookfinder.BuildConfig
import dev.suhockii.bookfinder.data.remote.GoogleDriveApi
import dev.suhockii.bookfinder.data.repository.GoogleDriveRepository
import dev.suhockii.bookfinder.di.provider.ApiProvider
import dev.suhockii.bookfinder.di.provider.GsonProvider
import dev.suhockii.bookfinder.di.provider.OkHttpClientProvider
import dev.suhockii.bookfinder.di.qualifier.DatabaseFileId
import dev.suhockii.bookfinder.di.qualifier.GoogleDrivePath
import dev.suhockii.bookfinder.domain.GoogleDriveInteractor
import dev.suhockii.bookfinder.domain.repositories.FileRepository
import okhttp3.OkHttpClient
import toothpick.config.Module

class ServerModule : Module() {
    init {
        //Network
        bind(String::class.java).withName(GoogleDrivePath::class.java).toInstance(BuildConfig.GOOGLE_DRIVE_URL)
        bind(String::class.java).withName(DatabaseFileId::class.java).toInstance(BuildConfig.DATABASE_FILE_ID)
        bind(Gson::class.java).toProvider(GsonProvider::class.java).singletonInScope()
        bind(GoogleDriveApi::class.java).toProvider(ApiProvider::class.java).singletonInScope()
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()

        //Repository
        bind(FileRepository::class.java).to(GoogleDriveRepository::class.java)
        bind(GoogleDriveInteractor::class.java)
    }
}