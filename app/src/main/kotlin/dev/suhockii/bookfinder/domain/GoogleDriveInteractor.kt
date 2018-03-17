package dev.suhockii.bookfinder.domain

import dev.suhockii.bookfinder.di.qualifier.DatabaseFileId
import dev.suhockii.bookfinder.domain.repositories.FileRepository
import dev.suhockii.bookfinder.util.ConstantValue
import javax.inject.Inject

class GoogleDriveInteractor @Inject constructor(
    private val fileRepository: FileRepository,
    @DatabaseFileId private val fileId: String
) {
    fun downloadDatabase() = fileRepository.getFile(fileId, ConstantValue.DOWNLOAD)
}