package dev.suhockii.bookfinder.ui;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dev.suhockii.bookfinder.ui.layout.MainActivityLayout
import org.jetbrains.anko.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityLayout().setContentView(this)
    }

    fun tryLogin(ui: AnkoContext<MainActivity>, name: CharSequence?, password: CharSequence?) {
        ui.doAsync {
            Thread.sleep(500)

            activityUiThreadWithContext {
                if (checkCredentials(name.toString(), password.toString())) {
                    toast("Logged in! :)")
                } else {
                    toast("Wrong password :( Enter user:password")
                }
            }
        }
    }

    private fun checkCredentials(name: String, password: String) =
        name == "user" && password == "password"
}

