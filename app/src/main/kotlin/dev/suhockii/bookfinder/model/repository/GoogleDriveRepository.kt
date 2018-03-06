package dev.suhockii.bookfinder.model.repository

import dev.suhockii.bookfinder.model.data.server.ConstantValue
import dev.suhockii.bookfinder.model.data.server.GoogleDriveApi
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) {
    fun getFile(
        fileId: String,
        exportType: String = ConstantValue.DOWNLOAD
    ) = googleDriveApi.getFile(fileId, exportType)
}