package suhockii.dev.bookfinder.di.module

import android.support.v7.widget.LinearLayoutManager
import suhockii.dev.bookfinder.di.provider.ui.CategoriesDifferProvider
import suhockii.dev.bookfinder.di.provider.ui.CategoryItemCallbackProvider
import suhockii.dev.bookfinder.di.provider.ui.LayoutManagerProvider
import suhockii.dev.bookfinder.presentation.categories.adapter.CategoriesAdapter
import suhockii.dev.bookfinder.presentation.categories.adapter.CategoriesDiffer
import suhockii.dev.bookfinder.presentation.categories.adapter.CategoryItemCallback
import toothpick.config.Module

class CategoriesActivityModule : Module() {
    init {
        bind(CategoriesAdapter::class.java).singletonInScope()
        bind(LinearLayoutManager::class.java).toProvider(LayoutManagerProvider::class.java).singletonInScope()
        bind(CategoriesDiffer::class.java).toProvider(CategoriesDifferProvider::class.java).singletonInScope()
        bind(CategoryItemCallback::class.java).toProvider(CategoryItemCallbackProvider::class.java).singletonInScope()
    }
}