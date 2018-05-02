package suhockii.dev.bookfinder.di.wrapper

import android.support.v7.util.DiffUtil
import suhockii.dev.bookfinder.domain.model.Category
import javax.inject.Inject

class CategoryItemCallback @Inject constructor() {

    private lateinit var value: DiffUtil.ItemCallback<Category>

    fun get(): DiffUtil.ItemCallback<Category> {
        return value
    }

    fun set(value: DiffUtil.ItemCallback<Category>) {
        this.value = value
    }
}