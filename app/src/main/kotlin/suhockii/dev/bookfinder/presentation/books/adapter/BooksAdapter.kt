package suhockii.dev.bookfinder.presentation.books.adapter

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContextImpl
import org.jetbrains.anko.AnkoLogger
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.domain.model.Book
import toothpick.Toothpick
import javax.inject.Inject


class BooksAdapter @Inject constructor() :
    RecyclerView.Adapter<BookViewHolder>(), AnkoLogger {

    private lateinit var differ: AsyncListDiffer<Book>
    private lateinit var onBookClickListener: OnBookClickListener

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (!this::differ.isInitialized) differ =
                Toothpick.openScopes(DI.APP_SCOPE, DI.BOOKS_ACTIVITY_SCOPE)
                    .getInstance(BooksDiffer::class.java).get()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val bookItemLayout = Toothpick.openScopes(DI.APP_SCOPE, DI.CATEGORIES_ACTIVITY_SCOPE)
            .getInstance(BookItemUI::class.java)
        bookItemLayout.createView(AnkoContextImpl(parent.context, parent, false))

        val viewHolder = BookViewHolder(bookItemLayout)
        viewHolder.itemView.setOnClickListener {
            val book = differ.currentList[viewHolder.adapterPosition]
            onBookClickListener.onBookClick(book)
        }
        return viewHolder
    }

    override fun getItemCount(): Int =
        differ.currentList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    fun submitList(list: List<Book>) =
        mutableListOf<Book>().apply {
            addAll(list)
            differ.submitList(this)
        }

    fun setOnBookClickListener(onBookClickListener: OnBookClickListener) {
        this.onBookClickListener = onBookClickListener
    }
}