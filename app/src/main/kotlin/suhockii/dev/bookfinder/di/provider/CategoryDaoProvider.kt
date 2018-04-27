package suhockii.dev.bookfinder.di.provider

import suhockii.dev.bookfinder.data.database.BooksDatabase
import suhockii.dev.bookfinder.data.database.dao.CategoryDao
import javax.inject.Inject
import javax.inject.Provider

class CategoryDaoProvider @Inject constructor(
    private val booksDatabase: BooksDatabase
) : Provider<CategoryDao> {

    override fun get() = booksDatabase.categoryDao()
}