package suhockii.dev.bookfinder.presentation.books.adapter

import suhockii.dev.bookfinder.domain.model.Book

interface OnBookClickListener {
    fun onBookClick(book: Book)
}