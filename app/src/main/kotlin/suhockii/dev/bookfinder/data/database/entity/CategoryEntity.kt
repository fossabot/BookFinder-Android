package suhockii.dev.bookfinder.data.database.entity

import android.arch.persistence.room.Entity
import android.os.Parcel
import android.os.Parcelable
import suhockii.dev.bookfinder.domain.model.Category

@Entity(
    tableName = "Categories",
    primaryKeys = ["name"]
)
data class CategoryEntity(
    override val name: String
) : Category {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryEntity> {
        override fun createFromParcel(parcel: Parcel): CategoryEntity {
            return CategoryEntity(parcel)
        }

        override fun newArray(size: Int): Array<CategoryEntity?> {
            return arrayOfNulls(size)
        }
    }
}