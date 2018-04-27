package suhockii.dev.bookfinder.di.provider

import suhockii.dev.bookfinder.data.database.BooksDatabase
import suhockii.dev.bookfinder.data.database.dao.BookDao
import javax.inject.Inject
import javax.inject.Provider

class BookDaoProvider @Inject constructor(
    private val booksDatabase: BooksDatabase
) : Provider<BookDao> {

    override fun get() = booksDatabase.booksDao()
}