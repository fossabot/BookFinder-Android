package suhockii.dev.bookfinder.data.network.interceptor

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException

internal class ProgressResponseBody(
    private val responseBody: ResponseBody,
    private val progressEmitter: ProgressEmitter
) : ResponseBody() {
    private var bufferedSource: BufferedSource? = null

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun source(): BufferedSource {
        if (bufferedSource == null) {
            val source = responseBody.source()
            bufferedSource = Okio.buffer(source(source))
        }
        return bufferedSource!!
    }

    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            internal var totalBytesRead = 0L

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val contentLength = responseBody.contentLength()
                val bytesRead = super.read(sink, byteCount)
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                val downloadedPercent = (totalBytesRead / contentLength.toDouble() * 100).toInt()
                progressEmitter.updateProgress(downloadedPercent, bytesRead == -1L)
                return bytesRead
            }
        }
    }
}
