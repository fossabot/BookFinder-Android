package suhockii.dev.bookfinder.presentation.initial

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.resolveAttrResource

class InitialActivityLayout : AnkoComponent<InitializationActivity> {
    internal lateinit var textTitle: TextView
    internal lateinit var textDescription: TextView
    internal lateinit var textProgress: TextView
    internal lateinit var progressViewGroup: View
    internal lateinit var btnExit: View
    internal lateinit var btnDownload: View
    internal lateinit var btnStop: View
    internal lateinit var btnContinue: View
    internal lateinit var btnRetry: View

    override fun createView(ui: AnkoContext<InitializationActivity>) = with(ui) {

        verticalLayout {
            frameLayout {
                backgroundResource = R.color.blue

                imageView(R.drawable.ic_info).lparams {
                    gravity = Gravity.CENTER
                    margin = dip(28)
                }
            }.lparams(matchParent, wrapContent)

            textView(R.string.info) {
                textTitle = this
                textSizeDimen = R.dimen.title
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
            }.lparams {
                topMargin = dip(24)
                leftMargin = dip(24)
                rightMargin = dip(24)
            }

            linearLayout {
                frameLayout {
                    progressViewGroup = this
                    visibility = View.GONE

                    themedProgressBar(R.style.ColoredProgressBar).lparams {
                        gravity = Gravity.CENTER
                    }

                    textView("0%") {
                        textProgress = this
                        textSize = 12F
                    }.lparams {
                        gravity = Gravity.CENTER
                    }
                }.lparams {
                    gravity = Gravity.CENTER_VERTICAL
                    leftMargin = dip(22)
                }

                textView(resources.getString(R.string.database_load_need)) {
                    textDescription = this
                    setPadding(0, dip(6), 0, dip(6))
                    textSizeDimen = R.dimen.description
                }.lparams {
                    leftMargin = dip(24)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }.lparams(matchParent, dip(64)) {
                topMargin = dip(16)
                rightMargin = dip(24)
            }

            frameLayout {
                backgroundResource = R.color.gray
            }.lparams(matchParent, dip(1)) {
                topMargin = dip(20)
                leftMargin = dip(16)
                rightMargin = dip(16)
            }

            linearLayout {
                textView(R.string.exit) {
                    btnExit = this
                    onClick { owner.finish() }
                }

                textView(R.string.download) {
                    btnDownload = this
                    onClick { owner.presenter.loadDatabase() }
                }

                textView(R.string.stop) {
                    btnStop = this
                    visibility = View.GONE
                    onClick { owner.presenter.stopDownloading() }
                }

                textView(R.string._continue) {
                    btnContinue = this
                    visibility = View.GONE
                    onClick { owner.presenter.flowFinished() }
                }

                textView(R.string.retry) {
                    btnRetry = this
                    visibility = View.GONE
                    onClick { owner.presenter.loadDatabase() }
                }
            }.applyRecursively { view ->
                when (view) {
                    is TextView -> with(view) {
                        backgroundResource = context.resolveAttrResource(R.attr.selectableItemBackground)
                        textSize = 14F
                        allCaps = true
                        textColorResource = R.color.blue
                        padding = dip(8)
                        lparams {
                            margin = dip(8)
                        }
                    }
                }
            }.lparams(wrapContent, dip(52)) {
                gravity = Gravity.END
            }
        }
    }
}
