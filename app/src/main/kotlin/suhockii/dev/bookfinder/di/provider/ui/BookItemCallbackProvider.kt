package suhockii.dev.bookfinder.di.provider.ui

import android.support.v7.util.DiffUtil
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.presentation.books.adapter.BookItemCallback
import javax.inject.Inject
import javax.inject.Provider

class BookItemCallbackProvider @Inject constructor() :
    Provider<BookItemCallback> {

    override fun get(): BookItemCallback = BookItemCallback().apply {
        set(object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.productCode == newItem.productCode
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.productCode == newItem.productCode
            }
        })
    }
}