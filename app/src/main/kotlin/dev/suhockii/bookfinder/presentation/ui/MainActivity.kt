package dev.suhockii.bookfinder.presentation.ui;

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dev.suhockii.bookfinder.di.DI
import dev.suhockii.bookfinder.presentation.mvp.main.MainActivityPresenter
import dev.suhockii.bookfinder.presentation.mvp.main.MainActivityView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import toothpick.Toothpick


class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MainActivityPresenter =
            Toothpick
                    .openScope(DI.SERVER_SCOPE)
                    .getInstance(MainActivityPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toothpick.openScopes(DI.SERVER_SCOPE, DI.MAIN_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@MainActivity, this)
        }

        MainActivityLayout().setContentView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.MAIN_ACTIVITY_SCOPE)
    }

    fun tryLogin(ui: AnkoContext<MainActivity>) {
        ui.doAsync {
            Thread.sleep(5000)
            activityUiThread {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Важное сообщение!")
                        .setMessage("Покормите кота!")
                        .setCancelable(false)
                        .setNegativeButton("ОК, иду на кухню",
                                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
                val alert = builder.create()
                alert.show()
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

            button("Log in") {
                onClick {
                    ui.owner.tryLogin(ui)
                }
            }

        }.applyRecursively(customStyle)
    }
}
