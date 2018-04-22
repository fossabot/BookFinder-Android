package suhockii.dev.bookfinder.data.database.entity

import android.arch.persistence.room.Entity
import suhockii.dev.bookfinder.domain.model.Category

@Entity(
    tableName = "Categories",
    primaryKeys = ["name"]
)
data class CategoryEntity(
    override val name: String
) : Category