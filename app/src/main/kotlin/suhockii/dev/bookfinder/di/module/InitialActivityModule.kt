package suhockii.dev.bookfinder.di.module

import android.content.Context
import android.os.Environment
import okhttp3.OkHttpClient
import suhockii.dev.bookfinder.BuildConfig
import suhockii.dev.bookfinder.data.network.CloudStorageApi
import suhockii.dev.bookfinder.data.network.interceptor.ProgressEmitter
import suhockii.dev.bookfinder.data.repository.LocalStorageRepository
import suhockii.dev.bookfinder.data.repository.GoogleDriveRepository
import suhockii.dev.bookfinder.di.provider.network.ApiProvider
import suhockii.dev.bookfinder.di.provider.network.OkHttpClientProvider
import suhockii.dev.bookfinder.di.DatabaseFileUrl
import suhockii.dev.bookfinder.di.DownloadDirectoryPath
import suhockii.dev.bookfinder.di.DownloadedFileName
import suhockii.dev.bookfinder.domain.repository.FileSystemRepository
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import suhockii.dev.bookfinder.presentation.initial.InitialUI
import toothpick.config.Module

class InitialActivityModule(context: Context) : Module() {
    init {
        bind(InitialUI::class.java).singletonInScope()

        //Network
        bind(String::class.java).withName(DownloadDirectoryPath::class.java).toInstance(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).path)
        bind(String::class.java).withName(DownloadedFileName::class.java).toInstance(BuildConfig.DOWNLOADED_FILE_NAME)
        bind(String::class.java).withName(DatabaseFileUrl::class.java).toInstance(BuildConfig.DATABASE_FILE_URL)
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(CloudStorageApi::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()
        bind(ProgressEmitter::class.java).singletonInScope()

        //Repository
        bind(ServerRepository::class.java).to(GoogleDriveRepository::class.java).singletonInScope()
        bind(FileSystemRepository::class.java).to(LocalStorageRepository::class.java).singletonInScope()
    }
}