package suhockii.dev.bookfinder.presentation.splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import suhockii.dev.bookfinder.domain.SplashInteractor
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val splashInteractor: SplashInteractor
) : MvpPresenter<SplashView>() {
    fun checkIfDatabaseLoaded() {
        if (splashInteractor.isDataLoaded()) viewState.showMainScreen()
        else viewState.showInitializationScreen()
    }
}