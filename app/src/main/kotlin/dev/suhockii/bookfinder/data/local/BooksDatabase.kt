package dev.suhockii.bookfinder.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import dev.suhockii.bookfinder.data.local.dao.BooksDao
import dev.suhockii.bookfinder.data.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}