package suhockii.dev.bookfinder.presentation.books.adapter

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import suhockii.dev.bookfinder.domain.model.Book
import javax.inject.Inject

class BooksDiffer @Inject constructor() {

    private lateinit var value: AsyncListDiffer<Book>

    fun get(): AsyncListDiffer<Book> {
        return value
    }

    fun set(value: AsyncListDiffer<Book>) {
        this.value = value
    }
}