package suhockii.dev.bookfinder.di.module

import android.support.v7.widget.LinearLayoutManager
import suhockii.dev.bookfinder.di.provider.ui.CategoriesDifferProvider
import suhockii.dev.bookfinder.di.provider.ui.CategoriesLayoutManagerProvider
import suhockii.dev.bookfinder.di.provider.ui.CategoryItemCallbackProvider
import suhockii.dev.bookfinder.di.wrapper.CategoriesDiffer
import suhockii.dev.bookfinder.di.wrapper.CategoryItemCallback
import suhockii.dev.bookfinder.presentation.categories.adapter.CategoriesAdapter
import toothpick.config.Module

class CategoriesActivityModule : Module() {
    init {
        //UI
        bind(CategoriesAdapter::class.java).singletonInScope()
        bind(LinearLayoutManager::class.java).toProvider(CategoriesLayoutManagerProvider::class.java).singletonInScope()
        bind(CategoriesDiffer::class.java).toProvider(CategoriesDifferProvider::class.java).singletonInScope()
        bind(CategoryItemCallback::class.java).toProvider(CategoryItemCallbackProvider::class.java).singletonInScope()
    }
}