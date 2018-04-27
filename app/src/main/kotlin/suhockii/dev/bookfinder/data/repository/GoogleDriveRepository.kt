package suhockii.dev.bookfinder.data.repository

import suhockii.dev.bookfinder.data.api.GoogleDriveApi
import suhockii.dev.bookfinder.domain.repository.ServerRepository
import javax.inject.Inject

class GoogleDriveRepository @Inject constructor(
    private val googleDriveApi: GoogleDriveApi
) : ServerRepository {

    override fun getFile(fileUrl: String): ByteArray =
        googleDriveApi.getFile(fileUrl).execute().body()!!.bytes()
}