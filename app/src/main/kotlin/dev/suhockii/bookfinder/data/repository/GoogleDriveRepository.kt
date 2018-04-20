package dev.suhockii.bookfinder.data.repository

import dev.suhockii.bookfinder.data.remote.GoogleDriveApi
import dev.suhockii.bookfinder.domain.repository.GoogleDriveRepository
import dev.suhockii.bookfinder.util.HttpHeader
import dev.suhockii.bookfinder.util.Regex
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : GoogleDriveRepository {

    override fun getFile(fileId: String): Pair<String, ByteArray> {
        val response = googleDriveApi.getFile(fileId)
        val contentDispositionHeader = response.headers[HttpHeader.CONTENT_DISPOSITION]!!.first()
        val fileNameRegex = Regex.FILE_NAME.toRegex()
        val fileName = fileNameRegex.find(contentDispositionHeader)!!.groups[0]!!.value
        return fileName to response.data
    }
}