package suhockii.dev.bookfinder.di.provider

import okhttp3.OkHttpClient
import suhockii.dev.bookfinder.data.api.interceptor.ProgressInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(
    private val progressInterceptor: ProgressInterceptor
): Provider<OkHttpClient> {

    override fun get(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(progressInterceptor)
            .build()
}