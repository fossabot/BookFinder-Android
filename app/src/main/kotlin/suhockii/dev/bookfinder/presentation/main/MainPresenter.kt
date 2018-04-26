package suhockii.dev.bookfinder.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import suhockii.dev.bookfinder.di.qualifier.DatabaseFileId
import suhockii.dev.bookfinder.domain.InitialInteractor
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val initialInteractor: InitialInteractor,
    @DatabaseFileId private val fileId: String
) : MvpPresenter<MainView>(), AnkoLogger {
}