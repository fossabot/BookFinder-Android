package dev.suhockii.bookfinder.data.repository

import dev.suhockii.bookfinder.di.qualifier.DownloadDirectoryPath
import dev.suhockii.bookfinder.domain.repository.FileRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import java.io.*
import java.util.zip.ZipInputStream
import javax.inject.Inject


class StorageRepository @Inject constructor(
    @DownloadDirectoryPath private val downloadDirectoryPath: String
) : FileRepository {

    override fun unzip(fromFile: File, toDirectory: File): Deferred<File> =
        async {
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
            outputFile
        }

    override fun saveFile(fileName: String, data: ByteArray): Deferred<File> =
        async {
            val file = File(downloadDirectoryPath, fileName)
            val bufferedOutputStream: BufferedOutputStream
            val fileOutputStream = FileOutputStream(file)
            bufferedOutputStream = BufferedOutputStream(fileOutputStream)
            bufferedOutputStream.write(data)
            bufferedOutputStream.flush()
            bufferedOutputStream.close()
            file
        }
}