package suhockii.dev.bookfinder.di.provider.ui

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.presentation.books.adapter.BookItemCallback
import suhockii.dev.bookfinder.presentation.books.adapter.BooksAdapter
import suhockii.dev.bookfinder.presentation.books.adapter.BooksDiffer
import javax.inject.Inject
import javax.inject.Provider

class BooksDifferProvider @Inject constructor(
    private val adapter: BooksAdapter,
    private val itemCallback: BookItemCallback
): Provider<BooksDiffer> {

    override fun get(): BooksDiffer = BooksDiffer().apply {
        set(AsyncListDiffer<Book>(adapter, itemCallback.get()))
    }
}