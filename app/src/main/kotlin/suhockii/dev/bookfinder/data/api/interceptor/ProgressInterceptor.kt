package suhockii.dev.bookfinder.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ProgressInterceptor @Inject constructor(
    private val progressListener: ProgressEmitter
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        return originalResponse.newBuilder()
            .body(ProgressResponseBody(originalResponse.body()!!, progressListener))
            .build()
    }
}