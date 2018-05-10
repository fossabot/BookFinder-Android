package suhockii.dev.bookfinder.data.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.os.Parcel
import android.os.Parcelable
import suhockii.dev.bookfinder.domain.model.Book

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
    override var category: String = "",
    override var shortName: String = "",
    override var fullName: String = "",
    override var price: Double = 0.0,
    override var iconLink: String = "",
    override var productLink: String = "",
    override var website: String = "",
    override var productCode: String = "",
    override var status: String = "",
    override var publisher: String? = null,
    override var author: String? = null,
    override var series: String? = null,
    override var format: String? = null,
    override var year: String? = null,
    override var pageCount: String? = null,
    override var cover: String? = null,
    override var description: String? = null,
    @Ignore var shortDescription: String = "",
    @Ignore var fullDescription: String = ""
) : Book {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(shortName)
        parcel.writeString(fullName)
        parcel.writeDouble(price)
        parcel.writeString(iconLink)
        parcel.writeString(productLink)
        parcel.writeString(website)
        parcel.writeString(productCode)
        parcel.writeString(status)
        parcel.writeString(publisher)
        parcel.writeString(author)
        parcel.writeString(series)
        parcel.writeString(format)
        parcel.writeString(year)
        parcel.writeString(pageCount)
        parcel.writeString(cover)
        parcel.writeString(description)
        parcel.writeString(shortDescription)
        parcel.writeString(fullDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookEntity> {
        override fun createFromParcel(parcel: Parcel): BookEntity {
            return BookEntity(parcel)
        }

        override fun newArray(size: Int): Array<BookEntity?> {
            return arrayOfNulls(size)
        }
    }
}