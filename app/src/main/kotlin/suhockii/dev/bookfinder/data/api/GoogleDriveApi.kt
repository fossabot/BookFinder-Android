package suhockii.dev.bookfinder.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GoogleDriveApi {

    @GET
    fun getFile(@Url fileUrl: String): Call<ResponseBody>
}