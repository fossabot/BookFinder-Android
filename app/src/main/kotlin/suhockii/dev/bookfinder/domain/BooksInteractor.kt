package suhockii.dev.bookfinder.domain

import suhockii.dev.bookfinder.domain.model.Category
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import javax.inject.Inject

class BooksInteractor @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    fun getBooks(category: Category) =
        databaseRepository.getBooksBy(category)
}