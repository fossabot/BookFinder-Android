package suhockii.dev.bookfinder.di.module

import android.content.Context
import android.os.Environment
import suhockii.dev.bookfinder.BuildConfig
import suhockii.dev.bookfinder.data.repository.DeviceStorageRepository
import suhockii.dev.bookfinder.data.repository.GoogleDriveRepository
import suhockii.dev.bookfinder.data.repository.RoomRepository
import suhockii.dev.bookfinder.data.repository.SharedPreferencesRepository
import suhockii.dev.bookfinder.data.server.GoogleDriveApi
import suhockii.dev.bookfinder.di.qualifier.DatabaseFileId
import suhockii.dev.bookfinder.di.qualifier.DownloadDirectoryPath
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import suhockii.dev.bookfinder.domain.repository.FileSystemRepository
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import suhockii.dev.bookfinder.domain.repository.SettingsRepository
import toothpick.config.Module

class ApiModule(context: Context) : Module() {
    init {
        //Network
        bind(String::class.java).withName(DatabaseFileId::class.java).toInstance(BuildConfig.DATABASE_FILE_ID)
        bind(String::class.java).withName(DownloadDirectoryPath::class.java).toInstance(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).path)

        //Repository
        bind(ServerRepository::class.java).to(GoogleDriveRepository::class.java)
        bind(FileSystemRepository::class.java).to(DeviceStorageRepository::class.java)
        bind(DatabaseRepository::class.java).to(RoomRepository::class.java)
        bind(SettingsRepository::class.java).to(SharedPreferencesRepository::class.java)
        bind(GoogleDriveApi::class.java).toInstance(GoogleDriveApi())
    }
}