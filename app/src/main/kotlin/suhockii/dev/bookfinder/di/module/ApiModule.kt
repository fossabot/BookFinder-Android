package suhockii.dev.bookfinder.di.module

import android.content.Context
import android.os.Environment
import okhttp3.OkHttpClient
import suhockii.dev.bookfinder.BuildConfig
import suhockii.dev.bookfinder.data.api.GoogleDriveApi
import suhockii.dev.bookfinder.data.api.interceptor.ProgressEmitter
import suhockii.dev.bookfinder.data.repository.DeviceStorageRepository
import suhockii.dev.bookfinder.data.repository.GoogleDriveRepository
import suhockii.dev.bookfinder.data.repository.RoomRepository
import suhockii.dev.bookfinder.data.repository.SharedPreferencesRepository
import suhockii.dev.bookfinder.di.provider.ApiProvider
import suhockii.dev.bookfinder.di.provider.OkHttpClientProvider
import suhockii.dev.bookfinder.di.qualifier.DatabaseFileUrl
import suhockii.dev.bookfinder.di.qualifier.DownloadDirectoryPath
import suhockii.dev.bookfinder.di.qualifier.DownloadedFileName
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import suhockii.dev.bookfinder.domain.repository.FileSystemRepository
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import suhockii.dev.bookfinder.domain.repository.SettingsRepository
import toothpick.config.Module

class ApiModule(context: Context) : Module() {
    init {
        //Network
        bind(String::class.java).withName(DownloadDirectoryPath::class.java).toInstance(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).path)
        bind(String::class.java).withName(DownloadedFileName::class.java).toInstance(BuildConfig.DOWNLOADED_FILE_NAME)
        bind(String::class.java).withName(DatabaseFileUrl::class.java).toInstance(BuildConfig.DATABASE_FILE_URL)
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(GoogleDriveApi::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()
        bind(ProgressEmitter::class.java).toInstance(ProgressEmitter())

        //Repository
        bind(ServerRepository::class.java).to(GoogleDriveRepository::class.java)
        bind(FileSystemRepository::class.java).to(DeviceStorageRepository::class.java)
        bind(DatabaseRepository::class.java).to(RoomRepository::class.java)
        bind(SettingsRepository::class.java).to(SharedPreferencesRepository::class.java)
    }
}