package suhockii.dev.bookfinder.data.server

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet

class GoogleDriveApi {
    init {
        FuelManager.instance.basePath = URL_GOOGLE_DRIVE
    }

    fun getFile(fileId: String): Response =
        URL_USER_CONTEXT.httpGet(
            listOf(
                HEADER_ID to fileId,
                HEADER_EXPORT to VALUE_DOWNLOAD
            )
        ).response().second

    companion object {
        const val URL_USER_CONTEXT: String = "/uc"
        const val URL_GOOGLE_DRIVE = "https://drive.google.com"
        const val HEADER_ID: String = "id"
        const val HEADER_EXPORT: String = "export"
        const val VALUE_DOWNLOAD: String = "download"
    }
}