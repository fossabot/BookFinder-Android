package dev.suhockii.bookfinder.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dev.suhockii.bookfinder.BuildConfig
import dev.suhockii.bookfinder.data.local.BooksDatabase
import dev.suhockii.bookfinder.data.local.dao.BookDao
import dev.suhockii.bookfinder.data.local.dao.CategoryDao
import dev.suhockii.bookfinder.data.remote.GoogleDriveApi
import dev.suhockii.bookfinder.data.xls.XlsParser
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        //Global
        bind(Context::class.java).toInstance(context)
        bind(XlsParser::class.java).toInstance(XlsParser())

        //Database
        val database = Room.databaseBuilder(context, BooksDatabase::class.java, BuildConfig.DATABASE_FILE_NAME).build()
        bind(BookDao::class.java).toInstance(database.booksDao())
        bind(CategoryDao::class.java).toInstance(database.categoryDao())
        bind(GoogleDriveApi::class.java).toInstance(GoogleDriveApi())
    }
}