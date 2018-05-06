package suhockii.dev.bookfinder.di.module

import android.support.v7.widget.LinearLayoutManager
import suhockii.dev.bookfinder.di.provider.ui.BookItemCallbackProvider
import suhockii.dev.bookfinder.di.provider.ui.BooksDifferProvider
import suhockii.dev.bookfinder.di.provider.ui.LayoutManagerProvider
import suhockii.dev.bookfinder.domain.model.Category
import suhockii.dev.bookfinder.presentation.books.adapter.BookItemCallback
import suhockii.dev.bookfinder.presentation.books.adapter.BooksAdapter
import suhockii.dev.bookfinder.presentation.books.adapter.BooksDiffer
import toothpick.config.Module

class BooksActivityModule(category: Category) : Module() {
    init {
        //UI
        bind(Category::class.java).toInstance(category)
        bind(BooksAdapter::class.java).singletonInScope()
        bind(LinearLayoutManager::class.java).toProvider(LayoutManagerProvider::class.java).singletonInScope()
        bind(BooksDiffer::class.java).toProvider(BooksDifferProvider::class.java).singletonInScope()
        bind(BookItemCallback::class.java).toProvider(BookItemCallbackProvider::class.java).singletonInScope()
    }
}