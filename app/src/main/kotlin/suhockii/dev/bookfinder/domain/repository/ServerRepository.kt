package suhockii.dev.bookfinder.domain.repository

interface ServerRepository {
    fun getFile(fileId: String): Pair<String, ByteArray>
}