package suhockii.dev.bookfinder.presentation.splash;

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.startActivity
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.presentation.initial.InitializationActivity
import suhockii.dev.bookfinder.presentation.categories.CategoriesActivity
import toothpick.Toothpick


class SplashActivity : MvpAppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter =
        Toothpick.openScope(DI.APP_SCOPE)
            .getInstance(SplashPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.openScopes(DI.APP_SCOPE, DI.SPLASH_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@SplashActivity, this)
        }
        presenter.checkIfDatabaseLoaded()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.SPLASH_ACTIVITY_SCOPE)
    }

    override fun showMainScreen() {
        startActivity<CategoriesActivity>()
        finish()
    }

    override fun showInitializationScreen() {
        startActivity<InitializationActivity>()
        finish()
    }
}