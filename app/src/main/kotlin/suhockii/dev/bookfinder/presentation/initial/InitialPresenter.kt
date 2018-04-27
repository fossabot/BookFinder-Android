package suhockii.dev.bookfinder.presentation.initial

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.data.ErrorHandler
import suhockii.dev.bookfinder.data.ErrorType
import suhockii.dev.bookfinder.domain.InitialInteractor
import java.util.concurrent.Future
import javax.inject.Inject

@InjectViewState
class InitialPresenter @Inject constructor(
    private val initialInteractor: InitialInteractor,
    private val errorHandler: ErrorHandler
) : MvpPresenter<InitialView>(), AnkoLogger {

    init {
        errorHandler.subscribe = {
            val errorDescriptionRes = when (it) {
                ErrorType.NETWORK -> R.string.error_network

                ErrorType.UNKNOWN -> R.string.error_unknown
            }
            doAsync { uiThread { viewState.showError(errorDescriptionRes) } }
        }
    }

    private lateinit var loadDatabaseTask: Future<Unit>

    fun loadDatabase() = with(initialInteractor) {
        doAsync(errorHandler.errorReceiver) {
            uiThread { viewState.showLoading() }
            val bytes = downloadDatabaseFile().get()
            val zipFile = saveDatabaseFile(bytes).get()
            uiThread { viewState.showUnzipping() }
            val xlsFile = unzip(zipFile, zipFile.parentFile).get()
            uiThread { viewState.showParsing() }
            val document = parseXlsDocument(xlsFile).get()
            uiThread { viewState.showSaving() }
            saveDocumentData(document.data).get()
            val (categoriesCount, booksCount) = getBooksAndCategoriesCount().get()
            uiThread { viewState.showSuccess(categoriesCount, booksCount) }
        }.apply { loadDatabaseTask = this }
    }

    fun stopDownloading() {
        loadDatabaseTask.cancel(true)
        viewState.showInitialViewState()
    }

    fun flowFinished() {
        initialInteractor.setDatabaseIsLoaded()
        viewState.showMainScreen()
    }
}
