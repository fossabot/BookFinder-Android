package dev.suhockii.bookfinder.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import dev.suhockii.bookfinder.data.local.dao.BookDao
import dev.suhockii.bookfinder.data.local.dao.CategoryDao
import dev.suhockii.bookfinder.data.local.entity.BookEntity
import dev.suhockii.bookfinder.data.local.entity.CategoryEntity

@Database(
    entities = [
        BookEntity::class,
        CategoryEntity::class
    ],
    version = 1
)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BookDao

    abstract fun categoryDao(): CategoryDao
}