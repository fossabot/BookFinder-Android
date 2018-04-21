package dev.suhockii.bookfinder.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import dev.suhockii.bookfinder.domain.model.Book

@Entity(
    tableName = "Books",
    primaryKeys = ["productCode"],
    foreignKeys = [
        (ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["name"],
            childColumns = ["category"]
        ))
    ]
)
data class BookEntity(
    override val category: String,
    override val shortName: String,
    override val fullName: String,
    override val shortDescription: String,
    override val fullDescription: String,
    override val price: Double,
    override val iconLink: String,
    override val productLink: String,
    override val website: String,
    override val productCode: String,
    override val status: String
) : Book