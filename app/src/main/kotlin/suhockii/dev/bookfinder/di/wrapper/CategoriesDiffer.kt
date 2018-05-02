package suhockii.dev.bookfinder.di.wrapper

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import suhockii.dev.bookfinder.domain.model.Category
import javax.inject.Inject

class CategoriesDiffer @Inject constructor() {

    private lateinit var value: AsyncListDiffer<Category>

    fun get(): AsyncListDiffer<Category> {
        return value
    }

    fun set(value: AsyncListDiffer<Category>) {
        this.value = value
    }
}