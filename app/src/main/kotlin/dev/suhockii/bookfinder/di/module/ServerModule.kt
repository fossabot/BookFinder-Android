package dev.suhockii.bookfinder.di.module

import android.content.Context
import android.os.Environment
import dev.suhockii.bookfinder.BuildConfig
import dev.suhockii.bookfinder.data.repository.StorageRepository
import dev.suhockii.bookfinder.di.qualifier.DatabaseFileId
import dev.suhockii.bookfinder.di.qualifier.DownloadDirectoryPath
import dev.suhockii.bookfinder.domain.repository.DatabaseRepository
import dev.suhockii.bookfinder.domain.repository.FileRepository
import dev.suhockii.bookfinder.domain.repository.GoogleDriveRepository
import toothpick.config.Module

class ServerModule(context: Context) : Module() {
    init {
        //Network
        bind(String::class.java).withName(DatabaseFileId::class.java).toInstance(BuildConfig.DATABASE_FILE_ID)
        bind(String::class.java).withName(DownloadDirectoryPath::class.java).toInstance(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).path)

        //Repository
        bind(GoogleDriveRepository::class.java).to(dev.suhockii.bookfinder.data.repository.GoogleDriveRepository::class.java)
        bind(FileRepository::class.java).to(StorageRepository::class.java)
        bind(DatabaseRepository::class.java).to(dev.suhockii.bookfinder.data.repository.DatabaseRepository::class.java)
    }
}