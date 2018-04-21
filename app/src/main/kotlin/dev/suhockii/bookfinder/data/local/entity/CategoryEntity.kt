package dev.suhockii.bookfinder.data.local.entity

import android.arch.persistence.room.Entity
import dev.suhockii.bookfinder.domain.model.Category

@Entity(
    tableName = "Categories",
    primaryKeys = ["name"]
)
data class CategoryEntity(
    override val name: String
) : Category