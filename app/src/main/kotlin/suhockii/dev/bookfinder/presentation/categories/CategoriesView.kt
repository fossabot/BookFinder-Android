package suhockii.dev.bookfinder.presentation.categories

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import suhockii.dev.bookfinder.domain.model.Category

@StateStrategyType(OneExecutionStateStrategy::class)
interface CategoriesView : MvpView {

    fun showCategories(categories: List<Category>)
}