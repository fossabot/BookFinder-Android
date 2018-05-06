package suhockii.dev.bookfinder.data.error

import android.content.Context
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import retrofit2.HttpException
import suhockii.dev.bookfinder.isAppOnForeground
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val context: Context
) {
    var subscriber: ((ErrorType) -> Unit)? = null

    val errorReceiver: (Throwable) -> Unit = {
        if (it !is InterruptedException &&
            it !is InterruptedIOException) {
            handleError(it)
        }
    }

    private fun handleError(it: Throwable) {
        it.printStackTrace()
        with(context) { if (isAppOnForeground()) runOnUiThread { toast(it.message.toString()) } }
        subscriber?.invoke(
            when {
                it.cause is UnknownHostException -> ErrorType.NETWORK

                it.cause is HttpException -> ErrorType.NETWORK

                it.cause is SocketTimeoutException -> ErrorType.NETWORK

                it.cause is OutOfMemoryError -> ErrorType.OUT_OF_MEMORY

                it.cause?.message?.contains(ERROR_MESSAGE_CORRUPTED_FILE) ?: false ->
                    ErrorType.CORRUPTED_FILE

                it.cause?.message?.contains(ERROR_MESSAGE_INVALID_HOSTNAME) ?: false ->
                    ErrorType.NETWORK

                else -> ErrorType.UNKNOWN
            }
        )
    }

    companion object {
        private const val ERROR_MESSAGE_CORRUPTED_FILE = "zipInputStream.nextEntry must not be null"
        private const val ERROR_MESSAGE_INVALID_HOSTNAME = "No address associated with hostname"
    }
}