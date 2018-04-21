package dev.suhockii.bookfinder.data.remote

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import dev.suhockii.bookfinder.util.ConstantValue
import dev.suhockii.bookfinder.util.HttpHeader
import dev.suhockii.bookfinder.util.RequestUrl

class GoogleDriveApi {
    init {
        FuelManager.instance.basePath = URL_GOOGLE_DRIVE
    }

    fun getFile(fileId: String): Response {
        val (request, response, result) = RequestUrl.USER_CONTEXT.httpGet(
            listOf(
                HttpHeader.ID to fileId,
                HttpHeader.EXPORT to ConstantValue.DOWNLOAD
            )
        ).response()
        return response
    }

    companion object {
        const val URL_GOOGLE_DRIVE = "https://drive.google.com"
    }
}