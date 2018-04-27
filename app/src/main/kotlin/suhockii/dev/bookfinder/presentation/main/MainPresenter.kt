package suhockii.dev.bookfinder.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
) : MvpPresenter<MainView>(), AnkoLogger {
}