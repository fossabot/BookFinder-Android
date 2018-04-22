package suhockii.dev.bookfinder.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import suhockii.dev.bookfinder.data.database.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert
    fun insertAll(categories: List<CategoryEntity>)

    @Query("SELECT * from Categories")
    fun getAll(): List<CategoryEntity>
}