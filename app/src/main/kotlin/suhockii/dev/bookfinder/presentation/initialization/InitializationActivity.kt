package suhockii.dev.bookfinder.presentation.initialization

import android.os.Bundle
import android.view.Gravity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.di.DI
import toothpick.Toothpick


class InitializationActivity : MvpAppCompatActivity(), InitializationView {

    @InjectPresenter
    lateinit var presenter: InitializationPresenter

    @ProvidePresenter
    fun providePresenter(): InitializationPresenter =
        Toothpick.openScope(DI.API_SCOPE)
            .getInstance(InitializationPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScopes(DI.API_SCOPE, DI.INITIALIZATION_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@InitializationActivity, this)
        }
        InitializationActivityLayout().setContentView(this)
        title = getString(R.string.initialization)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.MAIN_ACTIVITY_SCOPE)
    }
}

class InitializationActivityLayout : AnkoComponent<InitializationActivity> {
    override fun createView(ui: AnkoContext<InitializationActivity>) = with(ui) {
        verticalLayout {
            gravity = Gravity.CENTER
            weightSum = 3f

            textView(resources.getString(R.string.database_load_need)) {
                gravity = Gravity.CENTER
            }.lparams {
                margin = dip(32)
            }

            button(resources.getString(R.string.download)) {
                onClick {}
            }.lparams(wrapContent, wrapContent)

            textView(resources.getString(R.string.downloading)) {
                gravity = Gravity.CENTER
            }.lparams {
                margin = dip(32)
            }

            horizontalProgressBar {
                progress = 50
            }.lparams(matchParent, wrapContent) {
                margin = dip(32)
            }
        }
    }
}
