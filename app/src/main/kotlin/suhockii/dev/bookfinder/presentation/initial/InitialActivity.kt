package suhockii.dev.bookfinder.presentation.initial

import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.view.View
import android.view.Window
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textResource
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.di.module.InitialActivityModule
import suhockii.dev.bookfinder.presentation.categories.CategoriesActivity
import toothpick.Toothpick
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer


class InitializationActivity : MvpAppCompatActivity(), InitialView, AnkoLogger {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    @InjectPresenter
    lateinit var presenter: InitialPresenter

    @Inject
    lateinit var layout: InitialActivityLayout

    private lateinit var dotsTimer: Timer

    @ProvidePresenter
    fun providePresenter(): InitialPresenter =
        Toothpick.openScope(DI.APP_SCOPE)
            .apply { installModules(InitialActivityModule(this@InitializationActivity)) }
            .getInstance(InitialPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScopes(DI.APP_SCOPE, DI.INITIALIZATION_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@InitializationActivity, this)
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        layout.setContentView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.INITIALIZATION_ACTIVITY_SCOPE)
    }

    override fun update(downloadedPercent: Int, done: Boolean) {
        if (done) layout.textProgress.visibility = View.GONE
        else layout.textProgress.apply {
            text = getString(R.string.percent, downloadedPercent)
            visibility = View.VISIBLE
        }
    }

    override fun showLoading() = with(layout) {
        textProgress.visibility = View.VISIBLE
        textProgress.text = getString(R.string.percent, 0)
        textTitle.textResource = R.string.please_wait
        textDescription.textResource = R.string.downloading
        progressViewGroup.visibility = View.VISIBLE
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
        cancelDotsAnimation()
        progressViewGroup.visibility = View.GONE
        textDescription.text = getString(R.string.downloading_statistics, booksCount, categoriesCount)
        textTitle.textResource = R.string.success
        btnStop.visibility = View.GONE
        btnExit.visibility = View.GONE
        btnDownload.visibility = View.GONE
        btnContinue.visibility = View.VISIBLE
    }

    override fun showMainScreen() {
        startActivity<CategoriesActivity>()
        finish()
    }

    override fun showInitialViewState() = with(layout) {
        cancelDotsAnimation()
        btnDownload.visibility = View.VISIBLE
        btnExit.visibility = View.VISIBLE
        progressViewGroup.visibility = View.GONE
        btnStop.visibility = View.GONE
        textTitle.text = getString(R.string.info)
        textDescription.textResource = R.string.database_load_need
    }

    override fun showError(errorDescriptionRes: Int) = with(layout) {
        cancelDotsAnimation()
        textTitle.textResource = R.string.error
        textDescription.textResource = errorDescriptionRes
        btnRetry.visibility = View.VISIBLE
        btnStop.visibility = View.GONE
        btnExit.visibility = View.VISIBLE
        btnDownload.visibility = View.GONE
        progressViewGroup.visibility = View.GONE
    }

    private fun cancelDotsAnimation() {
        dotsTimer.cancel()
        val previousText = layout.textDescription.text.replace("\\.+".toRegex(), "")
        layout.textDescription.text = previousText
    }

    private fun startDotsAnimation() {
        var dotCount = 0
        dotsTimer = fixedRateTimer(period = DOTS_ANIMATION_DELAY) {
            dotCount++
            if (dotCount > MAX_DOTS_COUNT) dotCount = 0
            val dots = when (dotCount) {
                1 -> STRING_1_DOT
                2 -> STRING_2_DOT
                3 -> STRING_3_DOT
                else -> STRING_EMPTY
            }
            val previousText = layout.textDescription.text.replace("\\.+".toRegex(), "")
            runOnUiThread { layout.textDescription.text = previousText.plus(dots) }
        }
    }

    companion object {
        private const val DOTS_ANIMATION_DELAY = 350L
        private const val MAX_DOTS_COUNT = 3
        private const val STRING_EMPTY = ""
        private const val STRING_1_DOT = "."
        private const val STRING_2_DOT = ".."
        private const val STRING_3_DOT = "..."
    }
}
