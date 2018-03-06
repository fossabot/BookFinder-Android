package dev.suhockii.bookfinder.model.interactor

import dev.suhockii.bookfinder.model.repository.GoogleDriveRepository
import javax.inject.Inject

class GoogleDriveInteractor @Inject constructor(
    private val googleDriveRepository: GoogleDriveRepository
) {
    fun getFile(fileId: String) = googleDriveRepository.getFile(fileId = fileId)
}