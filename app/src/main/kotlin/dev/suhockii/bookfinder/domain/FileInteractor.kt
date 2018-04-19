package dev.suhockii.bookfinder.domain

import dev.suhockii.bookfinder.di.qualifier.LocalRepository
import dev.suhockii.bookfinder.di.qualifier.RemoteRepository
import dev.suhockii.bookfinder.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileInteractor @Inject constructor(
    @RemoteRepository private val remoteRepository: FileRepository,
    @LocalRepository private val localRepository: FileRepository
) {
    fun downloadDatabaseFile(fileId: String) =
        remoteRepository.getFile(fileId)

    fun saveDatabaseFile(fileName: String, data: ByteArray) =
        localRepository.saveFile(fileName, data)

    fun unzip(fromFile: File, toDirectory: File) =
        localRepository.unzip(fromFile, toDirectory)
}