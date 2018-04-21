package dev.suhockii.bookfinder.domain.repository

import dev.suhockii.bookfinder.domain.model.XlsDocument
import java.io.File

interface FileRepository {
    fun saveFile(fileName: String, data: ByteArray): File

    fun unzip(fromFile: File, toDirectory: File): File

    fun parseXlsDocument(xlsFile: File): XlsDocument
}