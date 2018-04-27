package suhockii.dev.bookfinder.di.module

import android.content.Context
import android.content.SharedPreferences
import suhockii.dev.bookfinder.BuildConfig
import suhockii.dev.bookfinder.data.ErrorHandler
import suhockii.dev.bookfinder.data.database.BooksDatabase
import suhockii.dev.bookfinder.data.database.dao.BookDao
import suhockii.dev.bookfinder.data.database.dao.CategoryDao
import suhockii.dev.bookfinder.data.parser.XlsParser
import suhockii.dev.bookfinder.di.provider.BookDaoProvider
import suhockii.dev.bookfinder.di.provider.BooksDatabaseProvider
import suhockii.dev.bookfinder.di.provider.CategoryDaoProvider
import suhockii.dev.bookfinder.di.provider.SharedPreferencesProvider
import suhockii.dev.bookfinder.di.qualifier.DatabaseFileName
import suhockii.dev.bookfinder.di.qualifier.SharedPreferencesFileName
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(String::class.java).withName(SharedPreferencesFileName::class.java).toInstance(BuildConfig.SHARED_PREFERENCES_FILE_NAME)
        bind(String::class.java).withName(DatabaseFileName::class.java).toInstance(BuildConfig.DATABASE_FILE_NAME)

        //Database
        bind(BooksDatabase::class.java).toProvider(BooksDatabaseProvider::class.java).providesSingletonInScope()
        bind(BookDao::class.java).toProvider(BookDaoProvider::class.java).providesSingletonInScope()
        bind(CategoryDao::class.java).toProvider(CategoryDaoProvider::class.java).providesSingletonInScope()

        //Tools
        bind(SharedPreferences::class.java).toProvider(SharedPreferencesProvider::class.java).providesSingletonInScope()
        bind(XlsParser::class.java).toInstance(XlsParser())
        bind(ErrorHandler::class.java).toInstance(ErrorHandler())
    }
}