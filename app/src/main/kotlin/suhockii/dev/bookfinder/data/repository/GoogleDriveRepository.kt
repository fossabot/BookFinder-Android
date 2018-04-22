package suhockii.dev.bookfinder.data.repository

import suhockii.dev.bookfinder.data.server.GoogleDriveApi
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : ServerRepository {

    override fun getFile(fileId: String): Pair<String, ByteArray> {
        val response = googleDriveApi.getFile(fileId)
        val contentDispositionHeader = response.headers[CONTENT_DISPOSITION]!!.first()
        val fileNameRegex = REGEX_FILE_NAME.toRegex()
        val fileName = fileNameRegex.find(contentDispositionHeader)!!.groups[0]!!.value
        return fileName to response.data
    }

    companion object {
        const val CONTENT_DISPOSITION: String = "Content-Disposition"
        const val REGEX_FILE_NAME: String = "(?<=filename=\")(.*\\n?)(?=\")"
    }
}