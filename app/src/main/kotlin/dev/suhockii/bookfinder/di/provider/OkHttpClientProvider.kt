package dev.suhockii.bookfinder.di.provider

import android.content.Context
import dev.suhockii.bookfinder.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(
        private val context: Context
) : Provider<OkHttpClient> {

    override fun get() : OkHttpClient = with(OkHttpClient.Builder()) {
        cache(Cache(context.cacheDir, 20 * 1024))
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            addNetworkInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }
        build()
    }
}