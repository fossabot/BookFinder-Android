package suhockii.dev.bookfinder.di.provider.ui

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import suhockii.dev.bookfinder.di.wrapper.CategoriesDiffer
import suhockii.dev.bookfinder.di.wrapper.CategoryItemCallback
import suhockii.dev.bookfinder.domain.model.Category
import suhockii.dev.bookfinder.presentation.categories.adapter.CategoriesAdapter
import javax.inject.Inject
import javax.inject.Provider

class CategoriesDifferProvider @Inject constructor(
    private val adapter: CategoriesAdapter,
    private val itemCallback: CategoryItemCallback
): Provider<CategoriesDiffer> {

    override fun get(): CategoriesDiffer = CategoriesDiffer().apply {
        set(AsyncListDiffer<Category>(adapter, itemCallback.get()))
    }
}