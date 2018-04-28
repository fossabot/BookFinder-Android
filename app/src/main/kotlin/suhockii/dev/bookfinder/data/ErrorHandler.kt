package suhockii.dev.bookfinder.data

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandler {
    var subscriber: ((ErrorType) -> Unit)? = null

    val errorReceiver: (Throwable) -> Unit = {
        it.printStackTrace()
        if (it !is InterruptedException) {
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
}