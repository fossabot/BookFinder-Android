package dev.suhockii.bookfinder.domain.repositories

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody

interface FileRepository {
    fun getFile(fileId: String, exportType: String): Deferred<ResponseBody>
}