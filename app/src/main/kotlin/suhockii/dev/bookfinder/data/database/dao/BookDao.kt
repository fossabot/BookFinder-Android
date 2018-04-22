package suhockii.dev.bookfinder.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import suhockii.dev.bookfinder.data.database.entity.BookEntity

@Dao
interface BookDao {

    @Insert
    fun insertAll(books: List<BookEntity>)

    @Query("SELECT * from Books")
    fun getAll(): List<BookEntity>

    @Query("SELECT * FROM Books WHERE category=:category")
    fun getAllByCategory(category: String): List<BookEntity>
}