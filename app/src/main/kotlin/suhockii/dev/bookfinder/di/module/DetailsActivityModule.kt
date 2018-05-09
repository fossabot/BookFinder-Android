package suhockii.dev.bookfinder.di.module

import suhockii.dev.bookfinder.domain.model.Book
import toothpick.config.Module

class DetailsActivityModule(book: Book) : Module() {
    init {
        bind(Book::class.java).toInstance(book)
    }
}