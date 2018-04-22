package suhockii.dev.bookfinder.data.repository

import suhockii.dev.bookfinder.data.parser.XlsParser
import suhockii.dev.bookfinder.di.qualifier.DownloadDirectoryPath
import suhockii.dev.bookfinder.domain.model.XlsDocument
import suhockii.dev.bookfinder.domain.repository.FileSystemRepository
import java.io.*
import java.util.zip.ZipInputStream
import javax.inject.Inject


class DeviceStorageRepository @Inject constructor(
    @DownloadDirectoryPath private val downloadDirectoryPath: String,
    private val xlsParser: XlsParser
) : FileSystemRepository {

    override fun saveFile(fileName: String, data: ByteArray): File {
        val file = File(downloadDirectoryPath, fileName)
        val bufferedOutputStream: BufferedOutputStream
        val fileOutputStream = FileOutputStream(file)
        bufferedOutputStream = BufferedOutputStream(fileOutputStream)
        bufferedOutputStream.write(data)
        bufferedOutputStream.flush()
        bufferedOutputStream.close()
        return file
    }

    override fun unzip(fromFile: File, toDirectory: File): File {
        lateinit var outputFile: File
        ZipInputStream(BufferedInputStream(FileInputStream(fromFile))).use { zipInputStream ->
            var count: Int = -1
            val buffer = ByteArray(8192)
            outputFile = File(toDirectory, zipInputStream.nextEntry.name)
            FileOutputStream(outputFile).use { fileOutputStream ->
                while (zipInputStream.read(buffer).apply { count = this } != -1) {
                    fileOutputStream.write(buffer, 0, count)
                }
            }
        }
        return outputFile
    }

    override fun parseXlsDocument(xlsFile: File): XlsDocument {
        return xlsParser.parseXlsDocument(xlsFile)
    }
}