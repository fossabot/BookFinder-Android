package suhockii.dev.bookfinder.di.provider.storage

import android.arch.persistence.room.Room
import android.content.Context
import suhockii.dev.bookfinder.data.database.BooksDatabase
import suhockii.dev.bookfinder.di.DatabaseFileName
import javax.inject.Inject
import javax.inject.Provider

class BooksDatabaseProvider @Inject constructor(
    private val context: Context,
    @DatabaseFileName private val databaseFileName: String
) : Provider<BooksDatabase> {

    override fun get(): BooksDatabase =
        Room.databaseBuilder(
            context,
            BooksDatabase::class.java,
            databaseFileName
        ).build()
}