package suhockii.dev.bookfinder.presentation.books

import android.graphics.Color
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.themedRecyclerView
import suhockii.dev.bookfinder.R
import javax.inject.Inject


class BooksActivityLayout @Inject constructor() : AnkoComponent<BooksActivity> {
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var emptyView: View

    override fun createView(ui: AnkoContext<BooksActivity>) = with(ui) {
        frameLayout {
            themedRecyclerView(R.style.ScrollbarRecyclerView) {
                id = R.id.id_recycler_books
                recyclerView = this
                clipToPadding = false
                setPadding(0, dip(8), 0, dip(8))
            }

            themedProgressBar(R.style.ColoredProgressBar) {
                progressBar = this
                visibility = View.GONE
            }.lparams {
                gravity = Gravity.CENTER
            }

            verticalLayout {
                emptyView = this
                gravity = Gravity.CENTER
                visibility = View.GONE

                imageView(R.drawable.ic_info).apply {
                    DrawableCompat.wrap(drawable).apply {
                        DrawableCompat.setTint(this, Color.GRAY)
                        setImageDrawable(this)
                    }
                }

                textView(R.string.empty_screen) {
                    gravity = Gravity.CENTER
                }.lparams {
                    topMargin = dip(16)
                    bottomMargin = dip(16)
                }
            }.lparams {
                gravity = Gravity.CENTER
                rightMargin = dip(56)
                leftMargin = dip(56)
            }
        }
    }
}