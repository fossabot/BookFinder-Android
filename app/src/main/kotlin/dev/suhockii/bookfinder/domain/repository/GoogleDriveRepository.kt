package dev.suhockii.bookfinder.domain.repository

interface GoogleDriveRepository {
    fun getFile(fileId: String): Pair<String, ByteArray>
}