package dev.suhockii.bookfinder.presentation.mvp.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dev.suhockii.bookfinder.di.qualifier.DatabaseFileId
import dev.suhockii.bookfinder.domain.FileInteractor
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject constructor(
    private val fileInteractor: FileInteractor,
    @DatabaseFileId private val fileId: String
) : MvpPresenter<MainActivityView>(), AnkoLogger {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        async {
            val (fileName, data) = fileInteractor.downloadDatabaseFile(fileId).await()
            val zipFile = fileInteractor.saveDatabaseFile(fileName, data).await()
            val xlsFile = fileInteractor.unzip(zipFile, zipFile.parentFile).await()
            val document = fileInteractor.parseXlsDocument(xlsFile).await()
            info { xlsFile.length() }
        }
    }
}