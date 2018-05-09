package suhockii.dev.bookfinder.presentation.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import suhockii.dev.bookfinder.domain.model.Book
import javax.inject.Inject

@InjectViewState
class DetailsPresenter @Inject constructor(
    private val book: Book
) : MvpPresenter<DetailsView>(), AnkoLogger {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showBookDetails(book)
    }
}