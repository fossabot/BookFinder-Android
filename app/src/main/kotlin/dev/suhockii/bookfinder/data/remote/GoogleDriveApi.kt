package dev.suhockii.bookfinder.data.remote

import dev.suhockii.bookfinder.util.RequestHeader
import dev.suhockii.bookfinder.util.RequestUrl
import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleDriveApi {

    @GET(RequestUrl.USER_CONTEXT)
    fun getFile(
        @Query(RequestHeader.ID) id: String,
        @Query(RequestHeader.EXPORT) export: String
    ): Deferred<ResponseBody>
}