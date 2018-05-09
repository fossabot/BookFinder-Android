package suhockii.dev.bookfinder.presentation.details

import android.graphics.Point
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.support.v4.nestedScrollView
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.attrResource
import suhockii.dev.bookfinder.collapsingToolbarLayout2
import javax.inject.Inject


class DetailsUI @Inject constructor() : AnkoComponent<DetailsActivity>, AnkoLogger {

    lateinit var toolbar: Toolbar
    lateinit var ivBook: ImageView
    lateinit var appBarLayout: AppBarLayout
    lateinit var fabBuy: View
    lateinit var tvFullName: TextView
    lateinit var tvPrice: TextView
    lateinit var tvISBN: TextView
    lateinit var tvAuthor: TextView
    lateinit var tvPublisher: TextView
    lateinit var tvYear: TextView
    lateinit var tvCover: TextView
    lateinit var tvPageCount: TextView
    lateinit var tvSeries: TextView
    lateinit var tvId: TextView
    lateinit var tvId2: TextView
    lateinit var tvAvailability: TextView
    lateinit var tvDeadlines: TextView
    lateinit var tvWeight: TextView
    private var windowHeight = 0

    override fun createView(ui: AnkoContext<DetailsActivity>) = with(ui) {
        calculateWindowHeight(owner)

        coordinatorLayout {
            fitsSystemWindows = true

            themedAppBarLayout(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                id = R.id.id_app_bar_details
                appBarLayout = this
                fitsSystemWindows = true

                collapsingToolbarLayout2 {
                    maxLines = 2
                    fitsSystemWindows = true
                    setContentScrimResource(R.color.colorPrimary)
                    setExpandedTitleMargin(dip(16), dip(16), dip(16), dip(16))
                    setExpandedTitleTextAppearance(R.style.TextAppearance_AppCompat_Headline)

                    frameLayout {
                        foreground = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.fade,
                            context.theme
                        )

                        imageView {
                            ivBook = this
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }.lparams(matchParent, matchParent)
                    }.lparams(matchParent, matchParent) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
                    }

                    toolbar {
                        setContentInsetsRelative(dip(16), dip(16))
                        toolbar = this
                        popupTheme = R.style.ThemeOverlay_AppCompat_Light
                    }.lparams(
                        matchParent,
                        with(context) { dimen(attrResource(R.attr.actionBarSize)) }) {
                        collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
                    }
                }.lparams(matchParent, matchParent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                            AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

            }.lparams(matchParent, windowHeight / 2)

            nestedScrollView {
                clipToPadding = false

                verticalLayout {
                    textView { tvFullName = this }

                    view { backgroundResource = R.color.gray }.lparams(matchParent, dip(1))

                    textView { tvPrice = this }

                    linearLayout {
                        textView(R.string.isbn).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvISBN = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.author).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvAuthor = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.publisher).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvPublisher = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.year).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvYear = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.cover).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvCover = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.pages).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvPageCount = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.series).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvSeries = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.id).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvId = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.id2).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvId2 = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.availability).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvAvailability = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.deadlines).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvDeadlines = this }.lparams(0, matchParent) { weight = 0.5f }
                    }

                    linearLayout {
                        textView(R.string.weight).lparams(0, matchParent) { weight = 0.5f }
                        textView { tvWeight = this }.lparams(0, matchParent) { weight = 0.5f }
                    }
                }

            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            floatingActionButton {
                id = R.id.id_fab
                fabBuy = this
                useCompatPadding = true
                imageResource = R.drawable.ic_buy
            }.lparams {
                anchorId = R.id.id_app_bar_details
                anchorGravity = Gravity.BOTTOM or Gravity.END
            }
        }
    }

    private fun calculateWindowHeight(owner: DetailsActivity) {
        val display = owner.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        windowHeight = size.y
    }
}