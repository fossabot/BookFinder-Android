package dev.suhockii.bookfinder.di.module

import android.content.Context
import android.os.Environment
import dev.suhockii.bookfinder.data.excel.entity.XlsParser
import dev.suhockii.bookfinder.di.qualifier.DownloadDirectoryPath
import dev.suhockii.bookfinder.util.ResourceManager
import dev.suhockii.bookfinder.util.flow.FlowRouter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        //Global
        bind(Context::class.java).toInstance(context)
        bind(ResourceManager::class.java).singletonInScope()
        bind(String::class.java).withName(DownloadDirectoryPath::class.java).toInstance(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).path)
        bind(XlsParser::class.java).toInstance(XlsParser())

        //Navigation
        val cicerone = Cicerone.create(FlowRouter())
        bind(Router::class.java).toInstance(cicerone.router)
        bind(FlowRouter::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}