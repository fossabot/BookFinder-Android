package suhockii.dev.bookfinder.data.error

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {
    var subscriber: ((ErrorType) -> Unit)? = null

    val errorReceiver: (Throwable) -> Unit = {
        if (it !is InterruptedException) handleError(it)
        it.printStackTrace()
    }

    private fun handleError(it: Throwable) {
        subscriber?.invoke(
            when {
                it.cause is UnknownHostException -> ErrorType.NETWORK

                it.cause is HttpException -> ErrorType.NETWORK

                it.cause is SocketTimeoutException -> ErrorType.NETWORK

                it.cause is OutOfMemoryError -> ErrorType.OUT_OF_MEMORY

                it.cause?.message?.contains(ERROR_MESSAGE_CORRUPTED_FILE) ?: false ->
                    ErrorType.CORRUPTED_FILE

                else -> ErrorType.UNKNOWN
            }
        )
    }

    companion object {
        private const val ERROR_MESSAGE_CORRUPTED_FILE = "zipInputStream.nextEntry must not be null"
    }
}