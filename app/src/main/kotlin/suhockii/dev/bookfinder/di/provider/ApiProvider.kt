package suhockii.dev.bookfinder.di.provider

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import suhockii.dev.bookfinder.data.api.GoogleDriveApi
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
    private val okHttpClient: OkHttpClient
): Provider<GoogleDriveApi> {

    override fun get(): GoogleDriveApi =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://vk.com/feed/")
                .build()
                .create(GoogleDriveApi::class.java)
}