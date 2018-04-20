package dev.suhockii.bookfinder.domain.model

interface Book {
    val category: String
    val shortName: String
    val fullName: String
    val shortDescription: String
    val fullDescription: String
    val price: Double
    val iconLink: String
    val productLink: String
    val productCode: String
    val website: String
    val status: String
}