package dev.suhockii.bookfinder.ui.layout

import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import dev.suhockii.bookfinder.ui.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

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

            imageView(android.R.drawable.ic_menu_manage).lparams {
                margin = dip(16)
                gravity = Gravity.CENTER
            }

            val name = editText {
                hint = "123"
            }
            val password = editText {
                hint = "R.string.password"
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            button("Log in") {
                onClick {
                    ui.owner.tryLogin(ui, name.text, password.text)
                }
            }

        }.applyRecursively(customStyle)
    }
}