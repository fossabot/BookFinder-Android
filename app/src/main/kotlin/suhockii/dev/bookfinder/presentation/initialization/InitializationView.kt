package suhockii.dev.bookfinder.presentation.initialization

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface InitializationView : MvpView {
    fun showLoading()

    fun showUnzipping()

    fun showParsing()

    fun showSaving()

    fun showCategoriesAndBooksCount(categoriesCount: Int, booksCount: Int)
}