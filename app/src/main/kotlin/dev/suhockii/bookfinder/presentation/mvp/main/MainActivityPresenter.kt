package dev.suhockii.bookfinder.presentation.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.suhockii.bookfinder.di.qualifier.DatabaseFileId
import dev.suhockii.bookfinder.domain.InitialInteractor
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(
    private val initialInteractor: InitialInteractor,
    @DatabaseFileId private val fileId: String
) : MvpPresenter<MainActivityView>(), AnkoLogger {

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