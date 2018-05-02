package suhockii.dev.bookfinder.di.module

import android.content.Context
import android.content.SharedPreferences
import suhockii.dev.bookfinder.BuildConfig
import suhockii.dev.bookfinder.data.database.BooksDatabase
import suhockii.dev.bookfinder.data.database.dao.BookDao
import suhockii.dev.bookfinder.data.database.dao.CategoryDao
import suhockii.dev.bookfinder.data.error.ErrorHandler
import suhockii.dev.bookfinder.data.parser.XlsParser
import suhockii.dev.bookfinder.data.repository.RoomRepository
import suhockii.dev.bookfinder.data.repository.SharedPreferencesRepository
import suhockii.dev.bookfinder.di.DatabaseFileName
import suhockii.dev.bookfinder.di.SharedPreferencesFileName
import suhockii.dev.bookfinder.di.provider.storage.BookDaoProvider
import suhockii.dev.bookfinder.di.provider.storage.BooksDatabaseProvider
import suhockii.dev.bookfinder.di.provider.storage.CategoryDaoProvider
import suhockii.dev.bookfinder.di.provider.storage.SharedPreferencesProvider
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import suhockii.dev.bookfinder.domain.repository.SettingsRepository
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)

        //Database
        bind(String::class.java).withName(DatabaseFileName::class.java).toInstance(BuildConfig.DATABASE_FILE_NAME)
        bind(BooksDatabase::class.java).toProvider(BooksDatabaseProvider::class.java).providesSingletonInScope()
        bind(BookDao::class.java).toProvider(BookDaoProvider::class.java).providesSingletonInScope()
        bind(CategoryDao::class.java).toProvider(CategoryDaoProvider::class.java).providesSingletonInScope()
        bind(DatabaseRepository::class.java).to(RoomRepository::class.java).singletonInScope()

        //Shared Preferences
        bind(String::class.java).withName(SharedPreferencesFileName::class.java).toInstance(BuildConfig.SHARED_PREFERENCES_FILE_NAME)
        bind(SharedPreferences::class.java).toProvider(SharedPreferencesProvider::class.java).providesSingletonInScope()

        //Tools
        bind(XlsParser::class.java).singletonInScope()
        bind(ErrorHandler::class.java).singletonInScope()

        //Repository
        bind(SettingsRepository::class.java).to(SharedPreferencesRepository::class.java).singletonInScope()
    }
}