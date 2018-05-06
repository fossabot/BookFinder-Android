package suhockii.dev.bookfinder.presentation.books.adapter

import android.support.v7.util.DiffUtil
import suhockii.dev.bookfinder.domain.model.Book
import javax.inject.Inject

class BookItemCallback @Inject constructor() {

    private lateinit var value: DiffUtil.ItemCallback<Book>

    fun get(): DiffUtil.ItemCallback<Book> {
        return value
    }

    fun set(value: DiffUtil.ItemCallback<Book>) {
        this.value = value
    }
}