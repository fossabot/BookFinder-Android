package suhockii.dev.bookfinder.data.repository

import suhockii.dev.bookfinder.data.database.dao.BookDao
import suhockii.dev.bookfinder.data.database.dao.CategoryDao
import suhockii.dev.bookfinder.data.database.entity.BookEntity
import suhockii.dev.bookfinder.data.database.entity.CategoryEntity
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.domain.model.Category
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import javax.inject.Inject

class RoomRepository @Inject constructor(
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