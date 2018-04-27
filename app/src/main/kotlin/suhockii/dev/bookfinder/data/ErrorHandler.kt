package suhockii.dev.bookfinder.data

import retrofit2.HttpException
import java.net.UnknownHostException

class ErrorHandler {
    var subscribe: ((ErrorType) -> Unit)? = null

    val errorReceiver: (Throwable) -> Unit = {
        it.printStackTrace()
        if (it !is InterruptedException) {
            subscribe?.invoke(
                when (it.cause) {
                    is UnknownHostException -> ErrorType.NETWORK

                    is HttpException -> ErrorType.NETWORK

                    else -> ErrorType.UNKNOWN
                }
            )
        }
    }
}