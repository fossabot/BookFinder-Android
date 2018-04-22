package suhockii.dev.bookfinder.domain.repository

import suhockii.dev.bookfinder.domain.model.XlsDocument
import java.io.File

interface FileSystemRepository {
    fun saveFile(fileName: String, data: ByteArray): File

    fun unzip(fromFile: File, toDirectory: File): File

    fun parseXlsDocument(xlsFile: File): XlsDocument
}