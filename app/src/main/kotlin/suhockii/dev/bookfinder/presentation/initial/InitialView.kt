package suhockii.dev.bookfinder.presentation.initial

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface InitialView : MvpView {
    fun showStepNumber(stepNumer: Int)

    fun showLoading()

    fun showUnzipping()

    fun showParsing()

    fun showSaving()

    fun showSuccess(statistics: Pair<Int, Int>)

    fun showMainScreen()

    fun showInitialViewState()

    fun showError(@StringRes errorDescriptionRes: Int)

    fun update(downloadedPercent: Int, done: Boolean)
}