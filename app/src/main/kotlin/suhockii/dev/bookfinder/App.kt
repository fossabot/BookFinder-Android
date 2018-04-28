package suhockii.dev.bookfinder

import android.app.Application
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.di.module.ApiModule
import suhockii.dev.bookfinder.di.module.AppModule
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
            FactoryRegistryLocator.setRootRegistry(suhockii.dev.bookfinder.FactoryRegistry())
            MemberInjectorRegistryLocator.setRootRegistry(suhockii.dev.bookfinder.MemberInjectorRegistry())
        }
    }

    private fun initAppScope() {
        val appScope = Toothpick.openScope(DI.APP_SCOPE)
        appScope.installModules(AppModule(this))

        val apiScope = Toothpick.openScopes(DI.APP_SCOPE, DI.API_SCOPE)
        apiScope.installModules(ApiModule(this))
    }
}