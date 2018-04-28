package suhockii.dev.bookfinder.data

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler {
    var subscriber: ((ErrorType) -> Unit)? = null

    val errorReceiver: (Throwable) -> Unit = {
        if (it !is InterruptedException) handleError(it)
        it.printStackTrace()
    }

    private fun handleError(it: Throwable) {
        subscriber?.invoke(
            when (it.cause) {
                is UnknownHostException -> ErrorType.NETWORK

                is HttpException -> ErrorType.NETWORK

                is SocketTimeoutException -> ErrorType.NETWORK

                is OutOfMemoryError -> ErrorType.OUT_OF_MEMORY

                else -> ErrorType.UNKNOWN
            }
        )
    }
}