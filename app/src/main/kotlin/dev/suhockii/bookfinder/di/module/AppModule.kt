package dev.suhockii.bookfinder.di.module

import android.content.Context
import dev.suhockii.bookfinder.di.PrimitiveWrapper
import dev.suhockii.bookfinder.di.qualifier.DefaultPageSize
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.gitlabclient.model.system.ResourceManager
import ru.terrakok.gitlabclient.model.system.flow.FlowRouter
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        //Global
        bind(Context::class.java).toInstance(context)
        bind(PrimitiveWrapper::class.java).withName(DefaultPageSize::class.java)
            .toInstance(PrimitiveWrapper(20))
        bind(ResourceManager::class.java).singletonInScope()

        //Navigation
        val cicerone = Cicerone.create(FlowRouter())
        bind(Router::class.java).toInstance(cicerone.router)
        bind(FlowRouter::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}