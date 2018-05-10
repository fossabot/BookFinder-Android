package suhockii.dev.bookfinder.presentation.details

import android.os.Bundle
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.setContentView
import suhockii.dev.bookfinder.R
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.di.module.DetailsActivityModule
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.openLink
import suhockii.dev.bookfinder.presentation.books.BooksActivity
import toothpick.Toothpick
import javax.inject.Inject


class DetailsActivity : MvpAppCompatActivity(), DetailsView {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @Inject
    lateinit var layout: DetailsUI

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter =
        Toothpick.openScopes(DI.APP_SCOPE, DI.DETAILS_ACTIVITY_SCOPE)
            .apply {
                val book = intent.getParcelableExtra<Book>(BooksActivity.ARG_BOOK)
                installModules(DetailsActivityModule(book))
            }.getInstance(DetailsPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = Toothpick.openScopes(DI.APP_SCOPE, DI.DETAILS_ACTIVITY_SCOPE)
        Toothpick.inject(this@DetailsActivity, scope)
        layout.setContentView(this)
        setSupportActionBar(layout.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.DETAILS_ACTIVITY_SCOPE)
    }

    override fun showBookDetails(book: Book) = with(layout) {
        Picasso.get().load(book.productLink).into(layout.ivBook)
        supportActionBar!!.title = book.shortName
        fabBuy.setOnClickListener { openLink(book.website) }
        tvFullName.text = book.fullName
        tvPrice.text = getString(R.string.rubles, book.price)
        tvISBN.text = book.productCode
        tvAuthor.text = book.author
        tvPublisher.text = book.publisher
        tvYear.text = book.year
        tvCover.text = book.cover
        tvPageCount.text = book.pageCount
        tvSeries.text = book.series
        tvAvailability.text = book.status
        tvDescription.text = book.description
    }
}

