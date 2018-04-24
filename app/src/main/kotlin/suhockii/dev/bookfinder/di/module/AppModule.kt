package suhockii.dev.bookfinder.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import suhockii.dev.bookfinder.BuildConfig
import suhockii.dev.bookfinder.data.database.BooksDatabase
import suhockii.dev.bookfinder.data.database.dao.BookDao
import suhockii.dev.bookfinder.data.database.dao.CategoryDao
import suhockii.dev.bookfinder.data.parser.XlsParser
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bind(XlsParser::class.java).toInstance(XlsParser())

        //Database
        val database = Room.databaseBuilder(context, BooksDatabase::class.java, BuildConfig.DATABASE_FILE_NAME).build()
        bind(BookDao::class.java).toInstance(database.booksDao())
        bind(CategoryDao::class.java).toInstance(database.categoryDao())

        //Shared preferences
        bind(SharedPreferences::class.java).toInstance(context.getSharedPreferences(
            BuildConfig.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE))
    }
}