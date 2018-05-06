package suhockii.dev.bookfinder.presentation.categories

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import suhockii.dev.bookfinder.data.error.ErrorHandler
import suhockii.dev.bookfinder.domain.CategoriesInteractor
import javax.inject.Inject

@InjectViewState
class CategoriesPresenter @Inject constructor(
    private val interactor: CategoriesInteractor,
    private val errorHandler: ErrorHandler
) : MvpPresenter<CategoriesView>(), AnkoLogger {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        doAsync(errorHandler.errorReceiver) {
            interactor.getCategories()
                .let { categories -> uiThread { viewState.showCategories(categories) } }
        }
    }
}