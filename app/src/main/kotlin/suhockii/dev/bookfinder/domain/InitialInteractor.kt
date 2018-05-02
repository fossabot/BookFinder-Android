package suhockii.dev.bookfinder.domain

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
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
    fun downloadDatabaseFile() = doAsyncResult {
        serverRepository.getFile(fileUrl)
    }

    fun saveDatabaseFile(bytes: ByteArray) = doAsyncResult {
        fileSystemRepository.saveFile(downloadedFileName, bytes)
    }

    fun unzip(fromFile: File, toDirectory: File) = doAsyncResult {
        fileSystemRepository.unzip(fromFile, toDirectory)
    }

    fun parseXlsDocument(xlsFile: File) = doAsyncResult {
        fileSystemRepository.parseXlsDocument(xlsFile)
    }

    fun saveDocumentData(data: Map<String, Collection<Book>>) = doAsync {
        databaseRepository.saveCategories(data.keys)
        databaseRepository.saveBooks(data.values.flatMap { books -> books }.toList())
    }

    fun getBooksAndCategoriesCount() = doAsyncResult {
        val categoriesCount = databaseRepository.getCategories().count()
        val booksCount = databaseRepository.getBooks().count()
        categoriesCount to booksCount
    }

    fun setDatabaseIsLoaded() {
        settingsRepository.isDatabaseLoaded = true
    }
}