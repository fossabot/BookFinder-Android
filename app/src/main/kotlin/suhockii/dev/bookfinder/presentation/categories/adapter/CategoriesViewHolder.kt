package suhockii.dev.bookfinder.presentation.categories.adapter

import android.support.v7.widget.RecyclerView
import suhockii.dev.bookfinder.domain.model.Category


class CategoriesViewHolder(
    private var categoryItemLayout: CategoryItemLayout
) : RecyclerView.ViewHolder(categoryItemLayout.parent) {

    fun bind(category: Category) {
        categoryItemLayout.name.text = category.name
    }
}