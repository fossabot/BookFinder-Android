package suhockii.dev.bookfinder.presentation.categories.adapter

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContextImpl
import org.jetbrains.anko.AnkoLogger
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.domain.model.Category
import toothpick.Toothpick
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoryViewHolder>(), AnkoLogger {

    private lateinit var differ: AsyncListDiffer<Category>
    private lateinit var onCategoryClickListener: OnCategoryClickListener

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (!this::differ.isInitialized) differ =
                Toothpick.openScopes(DI.APP_SCOPE, DI.CATEGORIES_ACTIVITY_SCOPE)
                    .getInstance(CategoriesDiffer::class.java).get()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val categoryItemLayout = Toothpick.openScopes(DI.APP_SCOPE, DI.CATEGORIES_ACTIVITY_SCOPE)
            .getInstance(CategoryItemUI::class.java)
        categoryItemLayout.createView(AnkoContextImpl(parent.context, parent, false))
        val viewHolder = CategoryViewHolder(categoryItemLayout)
        viewHolder.itemView.setOnClickListener {
            val category = differ.currentList[viewHolder.adapterPosition]
            onCategoryClickListener.onCategoryClick(category)
        }
        return viewHolder
    }

    override fun getItemCount(): Int =
        differ.currentList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    fun submitList(list: List<Category>) =
        mutableListOf<Category>().apply {
            addAll(list)
            differ.submitList(this)
        }

    fun setOnCategoryClickListener(onCategoryClickListener: OnCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener
    }
}