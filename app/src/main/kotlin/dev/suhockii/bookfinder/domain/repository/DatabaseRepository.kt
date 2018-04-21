package dev.suhockii.bookfinder.domain.repository

import dev.suhockii.bookfinder.data.local.entity.BookEntity
import dev.suhockii.bookfinder.domain.model.Book
import dev.suhockii.bookfinder.domain.model.Category

interface DatabaseRepository {
    fun getCategories(): List<Category>

    fun saveCategories(categories: Set<String>)

    fun getBooks(): List<Book>

    fun saveBooks(books: List<Book>)

    fun getBooksBy(category: Category): List<BookEntity>
}