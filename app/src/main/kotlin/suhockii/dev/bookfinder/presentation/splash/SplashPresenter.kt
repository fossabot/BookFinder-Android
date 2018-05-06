package suhockii.dev.bookfinder.presentation.splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.doAsync
import suhockii.dev.bookfinder.data.error.ErrorHandler
import suhockii.dev.bookfinder.domain.SplashInteractor
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val interactor: SplashInteractor,
    private val errorHandler: ErrorHandler
) : MvpPresenter<SplashView>() {

    fun checkIfDatabaseLoaded() =
        doAsync(errorHandler.errorReceiver) {
            if (interactor.isDataLoaded()) viewState.showMainScreen()
            else viewState.showInitializationScreen()
        }
}