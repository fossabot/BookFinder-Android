package suhockii.dev.bookfinder.domain

import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import javax.inject.Inject

class CategoriesInteractor @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    fun getCategories() =
        databaseRepository.getCategories()
}