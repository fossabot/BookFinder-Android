package suhockii.dev.bookfinder.presentation.books

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import suhockii.dev.bookfinder.domain.model.Book

@StateStrategyType(AddToEndSingleStrategy::class)
interface BooksView : MvpView {

    fun showTitle(title: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showBooks(books: List<Book>)

    fun showEmptyScreen()

    fun showProgressVisible(visible: Boolean)
}