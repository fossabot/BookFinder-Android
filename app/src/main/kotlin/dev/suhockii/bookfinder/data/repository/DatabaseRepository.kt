package dev.suhockii.bookfinder.data.repository

import dev.suhockii.bookfinder.data.local.dao.BookDao
import dev.suhockii.bookfinder.data.local.dao.CategoryDao
import dev.suhockii.bookfinder.data.local.entity.BookEntity
import dev.suhockii.bookfinder.data.local.entity.CategoryEntity
import dev.suhockii.bookfinder.domain.model.Book
import dev.suhockii.bookfinder.domain.model.Category
import dev.suhockii.bookfinder.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val bookDao: BookDao,
    private val categoryDao: CategoryDao
) : DatabaseRepository {
    override fun getCategories(): List<Category> {
        return categoryDao.getAll()
    }

    override fun saveCategories(categories: Set<String>) {
        categoryDao.insertAll(categories.map { CategoryEntity(it) })
    }

    override fun getBooks(): List<BookEntity> {
        return bookDao.getAll()
    }

    override fun saveBooks(books: List<Book>) {
        bookDao.insertAll(books.map { it as BookEntity })
    }

    override fun getBooksBy(category: Category): List<BookEntity> {
        return bookDao.getAllByCategory(category.name)
    }
}