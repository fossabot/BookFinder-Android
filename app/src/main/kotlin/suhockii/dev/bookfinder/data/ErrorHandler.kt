package suhockii.dev.bookfinder.data

import com.github.kittinunf.fuel.core.HttpException
import org.jetbrains.anko.AnkoLogger
import java.net.UnknownHostException

class ErrorHandler : AnkoLogger {
    var subscribe: ((ErrorType) -> Unit)? = null

    val errorReceiver: (Throwable) -> Unit = {
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