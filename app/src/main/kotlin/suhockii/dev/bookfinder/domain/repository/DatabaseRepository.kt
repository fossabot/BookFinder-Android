package suhockii.dev.bookfinder.domain.repository

import suhockii.dev.bookfinder.data.database.entity.BookEntity
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.domain.model.Category

interface DatabaseRepository {
    fun getCategories(): List<Category>

    fun saveCategories(categories: Set<String>)

    fun getBooks(): List<Book>

    fun saveBooks(books: List<Book>)

    fun getBooksBy(category: Category): List<BookEntity>
}