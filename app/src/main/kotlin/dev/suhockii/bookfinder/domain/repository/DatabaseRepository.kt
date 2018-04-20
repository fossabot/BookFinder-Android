package dev.suhockii.bookfinder.domain.repository

import dev.suhockii.bookfinder.domain.model.Book

interface DatabaseRepository {
    fun saveCategories(categories: Set<String>)

    fun saveBooks(books: List<Book>)

    fun getBooks(): List<Book>
}