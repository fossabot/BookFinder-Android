package suhockii.dev.bookfinder.presentation.categories.adapter

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import javax.inject.Inject

class CategoryItemLayout @Inject constructor() : AnkoComponent<ViewGroup> {
    lateinit var parent: View
    lateinit var name: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        frameLayout {
            this@CategoryItemLayout.parent = this

            frameLayout {

                textView {
                    name = this
                    gravity = Gravity.CENTER_VERTICAL
                }.lparams(matchParent, matchParent)
            }.lparams(matchParent, dip(48)) {
                leftMargin = dip(16)
                rightMargin = dip(16)
            }
        }
    }
}