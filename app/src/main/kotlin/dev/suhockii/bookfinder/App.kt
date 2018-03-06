package dev.suhockii.bookfinder

import android.app.Application
import dev.suhockii.bookfinder.di.DI
import dev.suhockii.bookfinder.di.module.AppModule
import dev.suhockii.bookfinder.di.module.ServerModule
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initToothpick()
        initAppScope()
    }

    private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
            FactoryRegistryLocator.setRootRegistry(dev.suhockii.bookfinder.FactoryRegistry())
            MemberInjectorRegistryLocator.setRootRegistry(dev.suhockii.bookfinder.MemberInjectorRegistry())
        }
    }

    private fun initAppScope() {
        val appScope = Toothpick.openScope(DI.APP_SCOPE)
        appScope.installModules(AppModule(this))

        //By default we need init ServerScope for launch app
        val serverScope = Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE)
        serverScope.installModules(ServerModule())
    }
}