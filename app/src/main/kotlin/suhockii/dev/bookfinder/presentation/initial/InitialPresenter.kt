package suhockii.dev.bookfinder.presentation.initial

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.data.error.ErrorHandler
import suhockii.dev.bookfinder.data.error.ErrorType
import suhockii.dev.bookfinder.data.network.interceptor.ProgressEmitter
import suhockii.dev.bookfinder.domain.InitialInteractor
import java.util.concurrent.Future
import javax.inject.Inject

@InjectViewState
class InitialPresenter @Inject constructor(
    private val interactor: InitialInteractor,
    private val progressEmitter: ProgressEmitter,
    private val errorHandler: ErrorHandler
) : MvpPresenter<InitialView>(), AnkoLogger {

    private lateinit var loadDatabaseTask: Future<Unit>

    init {
        errorHandler.subscriber = {
            val errorDescriptionRes = when (it) {
                ErrorType.NETWORK -> R.string.error_network

                ErrorType.OUT_OF_MEMORY -> R.string.out_of_memory

                ErrorType.CORRUPTED_FILE -> R.string.corrupted_file

                ErrorType.UNKNOWN -> R.string.error_unknown
            }
            doAsync { uiThread { viewState.showError(errorDescriptionRes) } }
        }
    }

    override fun destroyView(view: InitialView?) {
        super.destroyView(view)
        progressEmitter.subscriber = null
    }

    fun loadDatabase() =
        doAsync(errorHandler.errorReceiver) {
            progressEmitter.subscriber = { progress, done -> onDownloadProgress(progress, done) }
            var stepNumber = 0
            uiThread { viewState.showLoading(); viewState.showStepNumber(++stepNumber) }
                .run { interactor.downloadDatabaseFile() }
                .let { bytes -> interactor.saveDatabaseFile(bytes) }
                .also { uiThread { viewState.showUnzipping(); viewState.showStepNumber(++stepNumber) } }
                .let { zipArchive -> interactor.unzip(zipArchive, zipArchive.parentFile) }
                .also { uiThread { viewState.showParsing(); viewState.showStepNumber(++stepNumber) } }
                .let { file -> interactor.parseXlsDocument(file) }
                .also { uiThread { viewState.showSaving(); viewState.showStepNumber(++stepNumber) } }
                .let { xlsDocument -> interactor.saveDocumentData(xlsDocument.data) }
                .let { interactor.getBooksAndCategoriesCount() }
                .also { statistics -> uiThread { viewState.showSuccess(statistics) } }
        }.apply { loadDatabaseTask = this }

    private fun onDownloadProgress(progress: Int, done: Boolean) =
        doAsync(errorHandler.errorReceiver) {
            uiThread { viewState.update(progress, done) }
        }

    fun stopDownloading() {
        doAsync(errorHandler.errorReceiver) {
            loadDatabaseTask.cancel(true)
            uiThread { viewState.showInitialViewState() }
        }
    }

    fun flowFinished() =
        doAsync(errorHandler.errorReceiver) {
            interactor.setDatabaseLoaded()
            uiThread { viewState.showMainScreen() }
        }
}
