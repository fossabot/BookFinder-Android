package suhockii.dev.bookfinder.presentation.main;

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.sdk25.coroutines.onClick
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.presentation.initialization.InitializationPresenter
import toothpick.Toothpick


class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): InitializationPresenter =
        Toothpick.openScope(DI.API_SCOPE)
            .getInstance(InitializationPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScopes(DI.API_SCOPE, DI.MAIN_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@MainActivity, this)
        }
        MainActivityLayout().setContentView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.MAIN_ACTIVITY_SCOPE)
    }

    fun tryLogin(ui: AnkoContext<MainActivity>) {
        ui.async {
            Thread.sleep(5000)
            uiThread {
            }
        }
    }
}

class MainActivityLayout : AnkoComponent<MainActivity> {
    private val customStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 20f
            is EditText -> v.textSize = 24f
        }
    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            padding = dip(32)

            button("Main") {
                onClick {
                    ui.owner.tryLogin(ui)
                }
            }

        }.applyRecursively(customStyle)
    }
}
