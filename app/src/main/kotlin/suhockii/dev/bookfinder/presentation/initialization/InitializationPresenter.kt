package suhockii.dev.bookfinder.presentation.initialization

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import suhockii.dev.bookfinder.di.qualifier.DatabaseFileId
import suhockii.dev.bookfinder.domain.InitialInteractor
import javax.inject.Inject

@InjectViewState
class InitializationPresenter @Inject constructor(
    private val initialInteractor: InitialInteractor,
    @DatabaseFileId private val fileId: String
) : MvpPresenter<InitializationView>(), AnkoLogger {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val (fileName, data) = initialInteractor.downloadDatabaseFile(fileId).get()
        val zipFile = initialInteractor.saveDatabaseFile(fileName, data).get()
        val xlsFile = initialInteractor.unzip(zipFile, zipFile.parentFile).get()
        val document = initialInteractor.parseXlsDocument(xlsFile).get()
        initialInteractor.saveDocumentData(document.data).get()
        info { xlsFile.length() }
    }
}