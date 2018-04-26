package suhockii.dev.bookfinder.presentation.initial

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.presentation.main.MainActivity
import toothpick.Toothpick
import java.util.*
import kotlin.concurrent.fixedRateTimer


class InitializationActivity : MvpAppCompatActivity(), InitialView {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    @InjectPresenter
    lateinit var presenter: InitialPresenter

    private lateinit var layout: InitializationActivityLayout
    private lateinit var dotsTimer: Timer

    @ProvidePresenter
    fun providePresenter(): InitialPresenter =
        Toothpick.openScope(DI.API_SCOPE)
            .getInstance(InitialPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScopes(DI.API_SCOPE, DI.INITIALIZATION_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@InitializationActivity, this)
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        layout = InitializationActivityLayout()
        layout.setContentView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.MAIN_ACTIVITY_SCOPE)
    }

    override fun showLoading() = with(layout) {
        textTitle.textResource = R.string.please_wait
        textDescription.textResource = R.string.downloading
        progress.visibility = View.VISIBLE
        btnStop.visibility = View.VISIBLE
        btnExit.visibility = View.GONE
        btnDownload.visibility = View.GONE
        btnRetry.visibility = View.GONE
        startDotsAnimation()
    }

    override fun showUnzipping() {
        layout.textDescription.textResource = R.string.unzipping
    }

    override fun showParsing() {
        layout.textDescription.textResource = R.string.parsing
    }

    override fun showSaving() {
        layout.textDescription.textResource = R.string.saving
    }

    override fun showSuccess(categoriesCount: Int, booksCount: Int) = with(layout) {
        dotsTimer.cancel()
        progress.visibility = View.GONE
        textDescription.text =
                getString(R.string.downloading_statistics, booksCount, categoriesCount)
        textTitle.textResource = R.string.success
        btnStop.visibility = View.GONE
        btnExit.visibility = View.GONE
        btnDownload.visibility = View.GONE
        btnContinue.visibility = View.VISIBLE
    }

    override fun showMainScreen() {
        startActivity<MainActivity>()
        finish()
    }

    override fun showInitialViewState() = with(layout) {
        dotsTimer.cancel()
        btnDownload.visibility = View.VISIBLE
        btnExit.visibility = View.VISIBLE
        progress.visibility = View.GONE
        btnStop.visibility = View.GONE
        textTitle.text = getString(R.string.info)
        textDescription.textResource = R.string.database_load_need
    }

    override fun showError(errorDescriptionRes: Int) = with(layout) {
        dotsTimer.cancel()
        textTitle.textResource = R.string.error
        textDescription.textResource = errorDescriptionRes
        btnRetry.visibility = View.VISIBLE
        btnStop.visibility = View.GONE
        btnExit.visibility = View.VISIBLE
        btnDownload.visibility = View.GONE
        progress.visibility = View.GONE
    }

    private fun startDotsAnimation() {
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
            runOnUiThread {
                val previousText = layout.textDescription.text.replace("\\.+".toRegex(), "")
                layout.textDescription.text = previousText.plus(downloadingText)
            }
        }
    }
}

class InitializationActivityLayout : AnkoComponent<InitializationActivity> {
    internal lateinit var textTitle: TextView
    internal lateinit var textDescription: TextView
    internal lateinit var progress: View
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
                themedProgressBar(R.style.ColoredProgressBar) {
                    this@InitializationActivityLayout.progress = this
                    visibility = View.GONE
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
                    allCaps = true
                    textColorResource = R.color.blue
                    padding = dip(8)
                    onClick { owner.finish() }
                }.lparams {
                    margin = dip(8)
                }

                textView(R.string.download) {
                    btnDownload = this
                    allCaps = true
                    textColorResource = R.color.blue
                    padding = dip(8)
                    onClick { owner.presenter.loadDatabase() }
                }.lparams {
                    margin = dip(8)
                }

                textView(R.string.stop) {
                    btnStop = this
                    visibility = View.GONE
                    allCaps = true
                    textColorResource = R.color.blue
                    padding = dip(8)
                    onClick { owner.presenter.stopDownloading() }
                }.lparams {
                    margin = dip(8)
                }

                textView(R.string._continue) {
                    btnContinue = this
                    visibility = View.GONE
                    allCaps = true
                    textColorResource = R.color.blue
                    padding = dip(8)
                    onClick { owner.presenter.flowFinished() }
                }.lparams {
                    margin = dip(8)
                }

                textView(R.string.retry) {
                    btnRetry = this
                    visibility = View.GONE
                    allCaps = true
                    textColorResource = R.color.blue
                    padding = dip(8)
                    onClick { owner.presenter.loadDatabase() }
                }.lparams {
                    margin = dip(8)
                }
            }.lparams(wrapContent, dip(52)) {
                gravity = Gravity.END
            }
        }
    }
}
