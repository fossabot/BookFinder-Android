package dev.suhockii.bookfinder.data.repository

import dev.suhockii.bookfinder.data.remote.GoogleDriveApi
import dev.suhockii.bookfinder.domain.repositories.FileRepository
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : FileRepository {

    override fun getFile(fileId: String, exportType: String) =
        googleDriveApi.getFile(fileId, exportType)
}