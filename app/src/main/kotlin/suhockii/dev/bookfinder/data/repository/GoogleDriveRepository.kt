package suhockii.dev.bookfinder.data.repository

import suhockii.dev.bookfinder.data.server.GoogleDriveApi
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : ServerRepository {

    override fun getFile(fileId: String): Pair<String, ByteArray> =
        DOWNLOADED_FILE_NAME to googleDriveApi.getFile(fileId)

    companion object {
        const val DOWNLOADED_FILE_NAME: String = "downloaded_bytes"
    }
}