package suhockii.dev.bookfinder.presentation.categories.adapter

import suhockii.dev.bookfinder.domain.model.Category

interface OnCategoryClickListener {
    fun onCategoryClick(category: Category)
}