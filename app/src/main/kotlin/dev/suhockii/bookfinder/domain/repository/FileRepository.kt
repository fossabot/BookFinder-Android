package dev.suhockii.bookfinder.domain.repository

import dev.suhockii.bookfinder.domain.model.XlsDocument
import kotlinx.coroutines.experimental.Deferred
import java.io.File

interface FileRepository {
    fun getFile(fileId: String): Deferred<Pair<String, ByteArray>> = TODO()

    fun saveFile(fileName: String, data: ByteArray): Deferred<File> = TODO()

    fun unzip(fromFile: File, toDirectory: File): Deferred<File> = TODO()

    fun parseXlsDocument(xlsFile: File): Deferred<XlsDocument> = TODO()
}