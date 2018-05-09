package suhockii.dev.bookfinder.presentation.books.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.squareup.picasso.Picasso
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.domain.model.Book


class BookViewHolder(
    private val layout: BookItemUI,
    private val context: Context = layout.parent.context
) : RecyclerView.ViewHolder(layout.parent) {

    fun bind(book: Book) {
        Picasso.get().load(book.iconLink).into(layout.icon)
        layout.name.text = book.shortName
        layout.price.text = context.getString(R.string.rubles, book.price)
    }
}