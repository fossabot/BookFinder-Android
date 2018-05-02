package suhockii.dev.bookfinder.di.provider.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import suhockii.dev.bookfinder.data.network.CloudStorageApi
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
    private val okHttpClient: OkHttpClient
): Provider<CloudStorageApi> {

    override fun get(): CloudStorageApi =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RANDOM_URL)
                .build()
                .create(CloudStorageApi::class.java)

    companion object {
        private const val RANDOM_URL = "https://vk.com/feed/"
    }
}