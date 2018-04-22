package suhockii.dev.bookfinder.domain

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.domain.repository.DatabaseRepository
import suhockii.dev.bookfinder.domain.repository.FileSystemRepository
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import java.io.File
import javax.inject.Inject

class InitialInteractor @Inject constructor(
    private val serverRepository: ServerRepository,
    private val fileSystemRepository: FileSystemRepository,
    private val databaseRepository: DatabaseRepository
) {
    fun downloadDatabaseFile(fileId: String) = doAsyncResult {
        serverRepository.getFile(fileId)
    }

    fun saveDatabaseFile(fileName: String, data: ByteArray) = doAsyncResult {
        fileSystemRepository.saveFile(fileName, data)
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
}