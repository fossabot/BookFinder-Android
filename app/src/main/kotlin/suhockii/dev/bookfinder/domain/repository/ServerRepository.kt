package suhockii.dev.bookfinder.domain.repository

interface ServerRepository {
    fun getFile(fileUrl: String): ByteArray
}