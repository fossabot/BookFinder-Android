package dev.suhockii.bookfinder.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import dev.suhockii.bookfinder.data.local.entity.BookEntity

@Dao
interface BookDao {

    @Insert
    fun insertAll(books: List<BookEntity>)

    @Query("SELECT * from Books")
    fun getAll(): List<BookEntity>
}