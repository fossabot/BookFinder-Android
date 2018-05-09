package suhockii.dev.bookfinder.data.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
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
) : Book {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
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
        parcel.writeString(shortDescription)
        parcel.writeString(fullDescription)
        parcel.writeDouble(price)
        parcel.writeString(iconLink)
        parcel.writeString(productLink)
        parcel.writeString(website)
        parcel.writeString(productCode)
        parcel.writeString(status)
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