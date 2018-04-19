package dev.suhockii.bookfinder.data.repository

import dev.suhockii.bookfinder.data.remote.GoogleDriveApi
import dev.suhockii.bookfinder.domain.repository.FileRepository
import dev.suhockii.bookfinder.util.HttpHeader
import dev.suhockii.bookfinder.util.Regex
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : FileRepository {

    override fun getFile(fileId: String): Deferred<Pair<String, ByteArray>> =
        async {
            val response = googleDriveApi.getFile(fileId).await()
            if (response.isSuccessful) {
                val body = response.body()
                val contentDispositionHeader =
                    response.headers().get(HttpHeader.CONTENT_DISPOSITION)!!
                val fileNameRegex = Regex.FILE_NAME.toRegex()
                val fileName = fileNameRegex.find(contentDispositionHeader)!!.groups[0]!!.value
                return@async fileName to body!!.bytes()
            }
            throw Exception()
        }
}