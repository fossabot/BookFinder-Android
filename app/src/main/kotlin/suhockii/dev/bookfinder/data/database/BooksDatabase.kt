package suhockii.dev.bookfinder.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import suhockii.dev.bookfinder.data.database.dao.BookDao
import suhockii.dev.bookfinder.data.database.dao.CategoryDao
import suhockii.dev.bookfinder.data.database.entity.BookEntity
import suhockii.dev.bookfinder.data.database.entity.CategoryEntity

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