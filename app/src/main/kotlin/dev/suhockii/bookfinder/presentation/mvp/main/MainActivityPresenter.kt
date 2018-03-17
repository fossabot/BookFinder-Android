package dev.suhockii.bookfinder.presentation.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.suhockii.bookfinder.domain.GoogleDriveInteractor
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.wtf
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(
        private val googleDriveInteractor: GoogleDriveInteractor
) : MvpPresenter<MainActivityView>(), AnkoLogger {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        async {
            val responseBody = googleDriveInteractor.downloadDatabase().await()
            wtf(responseBody.contentLength())
        }
    }
}