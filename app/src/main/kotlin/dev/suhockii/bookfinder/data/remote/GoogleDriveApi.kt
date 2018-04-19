package dev.suhockii.bookfinder.data.remote

import dev.suhockii.bookfinder.util.ConstantValue
import dev.suhockii.bookfinder.util.HttpHeader
import dev.suhockii.bookfinder.util.RequestUrl
import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleDriveApi {

    @GET(RequestUrl.USER_CONTEXT)
    fun getFile(
        @Query(HttpHeader.ID) id: String,
        @Query(HttpHeader.EXPORT) export: String = ConstantValue.DOWNLOAD
    ): Deferred<Response<ResponseBody>>
}