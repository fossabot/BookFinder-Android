package suhockii.dev.bookfinder.data.api.interceptor

import javax.inject.Inject

class ProgressEmitter @Inject constructor() {
    var subscriber: ((downloadedPercent: Int, done: Boolean) -> Unit)? = null

    fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
        subscriber?.apply {
            val downloadedPercent = (bytesRead / contentLength.toDouble() * 100).toInt()
            invoke(downloadedPercent, done)
        }
    }
}
