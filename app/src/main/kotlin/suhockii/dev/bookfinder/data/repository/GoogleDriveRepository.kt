package suhockii.dev.bookfinder.data.repository

import suhockii.dev.bookfinder.data.server.GoogleDriveApi
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : ServerRepository {

    override fun getFile(fileId: String): Pair<String, ByteArray> {
        val response = googleDriveApi.getFile(fileId)
        return DOWNLOADED_FILE_NAME to response.data
    }

    companion object {
        const val DOWNLOADED_FILE_NAME: String = "downloaded_database"
    }
}