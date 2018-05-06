package suhockii.dev.bookfinder.di.provider.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import javax.inject.Inject
import javax.inject.Provider

class LayoutManagerProvider @Inject constructor(
    private val context: Context
) : Provider<LinearLayoutManager> {

    override fun get(): LinearLayoutManager =
        LinearLayoutManager(context, LinearLayout.VERTICAL, false)
}