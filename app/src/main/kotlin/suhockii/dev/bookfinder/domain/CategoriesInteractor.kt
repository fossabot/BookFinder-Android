package suhockii.dev.bookfinder.domain

import org.jetbrains.anko.doAsyncResult
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import javax.inject.Inject

class CategoriesInteractor @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    fun getCategories() = doAsyncResult {
        databaseRepository.getCategories()
    }
}