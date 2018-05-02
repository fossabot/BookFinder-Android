package suhockii.dev.bookfinder.presentation.categories

import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dip
import org.jetbrains.anko.recyclerview.v7.recyclerView
import suhockii.dev.bookfinder.R
import javax.inject.Inject

class CategoriesActivityLayout @Inject constructor() : AnkoComponent<CategoriesActivity> {
    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<CategoriesActivity>) = with(ui) {
        recyclerView {
            id = R.id.id_recycler_categories
            recyclerView = this
            clipToPadding = false
            setPadding(0, dip(8), 0, dip(8))
        }
    }
}