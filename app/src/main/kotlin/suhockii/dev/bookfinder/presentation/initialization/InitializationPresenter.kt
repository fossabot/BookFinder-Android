package suhockii.dev.bookfinder.presentation.initialization

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import suhockii.dev.bookfinder.di.qualifier.DatabaseFileId
import suhockii.dev.bookfinder.domain.InitialInteractor
import javax.inject.Inject

@InjectViewState
class InitializationPresenter @Inject constructor(
    private val initialInteractor: InitialInteractor,
    @DatabaseFileId private val fileId: String
) : MvpPresenter<InitializationView>(), AnkoLogger {

    fun loadDatabase() = with(initialInteractor) {
        doAsync {
            uiThread { viewState.showLoading() }
            val (fileName, data) = downloadDatabaseFile(fileId).get()
            val zipFile = saveDatabaseFile(fileName, data).get()
            uiThread { viewState.showUnzipping() }
            val xlsFile = unzip(zipFile, zipFile.parentFile).get()
            uiThread { viewState.showParsing() }
            val document = parseXlsDocument(xlsFile).get()
            uiThread { viewState.showSaving() }
            saveDocumentData(document.data).get()
            val (categoriesCount, booksCount) = getBooksAndCategoriesCount().get()
            uiThread { viewState.showCategoriesAndBooksCount(categoriesCount, booksCount) }
        }
    }
}