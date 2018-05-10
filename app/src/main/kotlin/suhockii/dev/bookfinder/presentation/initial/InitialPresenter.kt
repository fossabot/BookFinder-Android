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

    fun loadDatabase() = doAsync(errorHandler.errorReceiver) {
        var currentStep = ProgressStep.LOADING
        progressEmitter.subscriber = { progress, done ->
            uiThread {
                viewState.showProgress(progress, done)
                if (currentStep == ProgressStep.ANALYZING) {
                    currentStep = ProgressStep.PARSING
                    viewState.showLoadingStep(ProgressStep.PARSING)
                }
            }
        }
        uiThread {
            viewState.showLoadingStep(ProgressStep.LOADING)
        }
        val bytes = interactor.downloadDatabaseFile()
        val zipFile = interactor.saveDatabaseFile(bytes)
        uiThread {
            viewState.showLoadingStep(ProgressStep.UNZIPPING)
        }
        val unzippedFile = interactor.unzip(zipFile, zipFile.parentFile)
        uiThread {
            currentStep = ProgressStep.ANALYZING
            viewState.showLoadingStep(ProgressStep.ANALYZING)
        }
        val xlsDocument = interactor.parseXlsDocument(unzippedFile)
        uiThread {
            viewState.showLoadingStep(ProgressStep.SAVING)
        }
        interactor.saveDocumentData(xlsDocument.data)
        val statistics = interactor.getBooksAndCategoriesCount()
        uiThread { viewState.showSuccess(statistics) }
    }.apply { loadDatabaseTask = this }

    fun stopDownloading() = doAsync(errorHandler.errorReceiver) {
        loadDatabaseTask.cancel(true)
        uiThread { viewState.showInitialViewState() }
    }

    fun flowFinished() = doAsync(errorHandler.errorReceiver) {
        interactor.setDatabaseLoaded()
        uiThread { viewState.showMainScreen() }
    }
}
