package dev.suhockii.bookfinder.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import dev.suhockii.bookfinder.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert
    fun insertAll(categories: List<CategoryEntity>)

    @Query("SELECT * from Categories")
    fun getAll(): List<CategoryEntity>
}