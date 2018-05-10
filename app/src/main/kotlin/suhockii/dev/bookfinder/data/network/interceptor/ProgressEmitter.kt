package suhockii.dev.bookfinder.data.network.interceptor

import javax.inject.Inject

class ProgressEmitter @Inject constructor() {
    var subscriber: ((progress: Int, done: Boolean) -> Unit)? = null

    fun updateProgress(progress: Int, done: Boolean) =
        subscriber?.invoke(progress, done)
}
