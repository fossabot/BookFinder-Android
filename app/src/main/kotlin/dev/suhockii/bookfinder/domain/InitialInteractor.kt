package dev.suhockii.bookfinder.domain

import dev.suhockii.bookfinder.domain.model.Book
import dev.suhockii.bookfinder.domain.repository.DatabaseRepository
import dev.suhockii.bookfinder.domain.repository.FileRepository
import dev.suhockii.bookfinder.domain.repository.GoogleDriveRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import java.io.File
import javax.inject.Inject

class InitialInteractor @Inject constructor(
    private val remoteRepository: GoogleDriveRepository,
    private val localRepository: FileRepository,
    private val databaseRepository: DatabaseRepository
) {
    fun downloadDatabaseFile(fileId: String) = doAsyncResult {
        remoteRepository.getFile(fileId)
    }

    fun saveDatabaseFile(fileName: String, data: ByteArray) = doAsyncResult {
        localRepository.saveFile(fileName, data)
    }

    fun unzip(fromFile: File, toDirectory: File) = doAsyncResult {
        localRepository.unzip(fromFile, toDirectory)
    }

    fun parseXlsDocument(xlsFile: File) = doAsyncResult {
        localRepository.parseXlsDocument(xlsFile)
    }

    fun saveDocumentData(data: Map<String, Collection<Book>>) = doAsync {
        databaseRepository.saveCategories(data.keys)
        databaseRepository.saveBooks(data.values.flatMap { books -> books }.toList())
    }
}