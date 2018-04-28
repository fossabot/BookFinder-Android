package suhockii.dev.bookfinder.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface GoogleDriveApi {

    @Streaming
    @GET
    fun getFile(@Url fileUrl: String): Call<ResponseBody>
}