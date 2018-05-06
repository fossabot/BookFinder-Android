package suhockii.dev.bookfinder.presentation.books.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*
import suhockii.dev.bookfinder.R
import javax.inject.Inject

class BookItemLayout @Inject constructor() : AnkoComponent<ViewGroup> {
    lateinit var parent: View
    lateinit var name: TextView
    lateinit var price: TextView
    lateinit var icon: ImageView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        frameLayout {
            this@BookItemLayout.parent = this

            linearLayout {

                imageView(R.drawable.ic_info) {
                    icon = this
                }.lparams(dip(56), dip(56)) {
                    margin = dip(8)
                }

                textView {
                    name = this
                    gravity = Gravity.CENTER_VERTICAL
                    ellipsize = TextUtils.TruncateAt.END
                    maxLines = 2
                }.lparams(dip(0), matchParent) {
                    weight = 1f
                    margin = dip(8)
                }

                textView {
                    price = this
                    gravity = Gravity.CENTER_VERTICAL or Gravity.END
                }.lparams(wrapContent, matchParent) {
                    margin = dip(8)
                }
            }.lparams(matchParent, dip(72)) {
                leftMargin = dip(8)
                rightMargin = dip(8)
            }
        }.apply {
            rootView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}