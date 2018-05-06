package suhockii.dev.bookfinder.domain

import suhockii.dev.bookfinder.di.DatabaseFileUrl
import suhockii.dev.bookfinder.di.DownloadedFileName
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import suhockii.dev.bookfinder.domain.repository.FileSystemRepository
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import suhockii.dev.bookfinder.domain.repository.SettingsRepository
import java.io.File
import javax.inject.Inject

class InitialInteractor @Inject constructor(
    private val serverRepository: ServerRepository,
    private val fileSystemRepository: FileSystemRepository,
    private val databaseRepository: DatabaseRepository,
    private val settingsRepository: SettingsRepository,
    @DatabaseFileUrl private val fileUrl: String,
    @DownloadedFileName private val downloadedFileName: String
) {
    fun downloadDatabaseFile() =
        serverRepository.getFile(fileUrl)

    fun saveDatabaseFile(bytes: ByteArray) =
        fileSystemRepository.saveFile(downloadedFileName, bytes)

    fun unzip(fromFile: File, toDirectory: File) =
        fileSystemRepository.unzip(fromFile, toDirectory)

    fun parseXlsDocument(xlsFile: File) =
        fileSystemRepository.parseXlsDocument(xlsFile)

    fun saveDocumentData(data: Map<String, Collection<Book>>) {
        databaseRepository.saveCategories(data.keys)
        databaseRepository.saveBooks(data.values.flatMap { books -> books }.toList())
    }

    fun getBooksAndCategoriesCount(): Pair<Int, Int> {
        val categoriesCount = databaseRepository.getCategories().count()
        val booksCount = databaseRepository.getBooks().count()
        return categoriesCount to booksCount
    }

    fun setDatabaseLoaded() {
        settingsRepository.isDatabaseLoaded = true
    }
}