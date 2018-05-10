package suhockii.dev.bookfinder.domain.model

import android.os.Parcelable

interface Book: Parcelable {
    val category: String
    val shortName: String
    val fullName: String
    val price: Double
    val iconLink: String
    val productLink: String
    val productCode: String
    val website: String
    val status: String
    val publisher: String?
    val author: String?
    val series: String?
    val format: String?
    val year: String?
    val pageCount: String?
    val cover: String?
    val description: String?
}