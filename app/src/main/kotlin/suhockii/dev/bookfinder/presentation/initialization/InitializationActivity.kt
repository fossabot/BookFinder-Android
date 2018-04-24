package suhockii.dev.bookfinder.presentation.initialization

import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.support.transition.AutoTransition
import android.support.transition.TransitionManager
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.coroutines.onClick
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.getResourceIdAttribute
import toothpick.Toothpick
import java.util.*
import kotlin.concurrent.fixedRateTimer


class InitializationActivity : MvpAppCompatActivity(), InitializationView {

    @InjectPresenter
    lateinit var presenter: InitializationPresenter
    lateinit var layout: InitializationActivityLayout

    @ProvidePresenter
    fun providePresenter(): InitializationPresenter =
        Toothpick.openScope(DI.API_SCOPE)
            .getInstance(InitializationPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScopes(DI.API_SCOPE, DI.INITIALIZATION_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@InitializationActivity, this)
        }
        layout = InitializationActivityLayout()
        layout.setContentView(this)
        title = getString(R.string.initialization)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.MAIN_ACTIVITY_SCOPE)
    }

    override fun showLoading() {
        layout.progressInfoTextView.text = getString(R.string.downloading)
        layout.showProgressView(this)
    }

    override fun showUnzipping() {
        layout.progressInfoTextView.text = getString(R.string.unzipping)
    }

    override fun showParsing() {
        layout.progressInfoTextView.text = getString(R.string.parsing)
    }

    override fun showSaving() {
        layout.progressInfoTextView.text = getString(R.string.saving)
    }

    override fun showCategoriesAndBooksCount(categoriesCount: Int, booksCount: Int) {
        toast("$categoriesCount, $booksCount")
    }
}

class InitializationActivityLayout : AnkoComponent<InitializationActivity> {
    internal lateinit var progressInfoTextView: TextView
    private lateinit var loadingViewGroup: View
    private lateinit var rootViewGroup: ViewGroup
    private lateinit var dotsTextView: TextView
    private lateinit var downloadButton: View
    private var dotsTimer: Timer? = null

    override fun createView(ui: AnkoContext<InitializationActivity>) = with(ui) {
        verticalLayout {
            gravity = Gravity.CENTER
            weightSum = 3f

            linearLayout {
                imageView(R.drawable.ic_info).lparams { gravity = Gravity.CENTER }

                textView(R.string.information) {
                    textSizeDimen = R.dimen.title
                    gravity = Gravity.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.CENTER
                    leftMargin = dip(8)
                }
            }.lparams { gravity = Gravity.CENTER }

            textView(resources.getString(R.string.database_load_need)) {
                gravity = Gravity.CENTER
            }.lparams { margin = dip(32) }

            linearLayout {
                downloadButton = this
                gravity = Gravity.CENTER

                cardView {
                    foreground = ContextCompat.getDrawable(
                        context,
                        context.getResourceIdAttribute(R.attr.selectableItemBackground)
                    )

                    onClick { owner.presenter.loadDatabase() }

                    linearLayout {
                        gravity = Gravity.CENTER

                        imageView(R.drawable.ic_download).lparams {
                            leftMargin = dip(12)
                        }

                        textView(R.string.download) {
                            textSizeDimen = R.dimen.button
                            padding = dip(12)
                        }.lparams(wrapContent, wrapContent)
                    }
                }.lparams(wrapContent, wrapContent)
            }.lparams(matchParent, sp(56))

            loadingViewGroup = linearLayout {
                visibility = View.GONE
                gravity = Gravity.CENTER

                progressBar {
                }.lparams(wrapContent, wrapContent) {
                    margin = dip(8)
                }

                progressInfoTextView = textView {
                    textSizeDimen = R.dimen.button
                }.lparams(wrapContent, wrapContent) {
                    leftMargin = dip(8)
                    gravity = Gravity.CENTER_VERTICAL
                }

                dotsTextView = textView {
                    textSizeDimen = R.dimen.button
                }.lparams(sp(20), wrapContent) {
                    rightMargin = dip(8)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }.lparams(wrapContent, sp(56))
        }.apply { rootViewGroup = this }
    }

    fun showProgressView(owner: Activity) {
        TransitionManager.beginDelayedTransition(rootViewGroup, AutoTransition())
        downloadButton.visibility = View.GONE
        loadingViewGroup.visibility = View.VISIBLE
        startDotsAnimation(owner)
    }

    private fun startDotsAnimation(owner: Activity) {
        dotsTimer?.cancel()
        var dotCount = 0
        dotsTimer = fixedRateTimer(period = 350) {
            dotCount++
            if (dotCount == 4) dotCount = 0
            val downloadingText = when (dotCount) {
                0 -> ""
                1 -> "."
                2 -> ".."
                else -> "..."
            }
            owner.runOnUiThread { dotsTextView.text = downloadingText }
        }
    }
}
