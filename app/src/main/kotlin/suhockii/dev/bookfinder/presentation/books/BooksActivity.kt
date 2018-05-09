package suhockii.dev.bookfinder.presentation.books

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import suhockii.dev.bookfinder.di.DI
import suhockii.dev.bookfinder.di.module.BooksActivityModule
import suhockii.dev.bookfinder.domain.model.Book
import suhockii.dev.bookfinder.domain.model.Category
import suhockii.dev.bookfinder.presentation.books.adapter.BooksAdapter
import suhockii.dev.bookfinder.presentation.books.adapter.OnBookClickListener
import suhockii.dev.bookfinder.presentation.categories.CategoriesActivity
import suhockii.dev.bookfinder.presentation.details.DetailsActivity
import toothpick.Toothpick
import javax.inject.Inject


class BooksActivity : MvpAppCompatActivity(), BooksView, OnBookClickListener {

    @InjectPresenter
    lateinit var presenter: BooksPresenter

    @Inject
    lateinit var layout: BooksUI

    @Inject
    lateinit var adapter: BooksAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @ProvidePresenter
    fun providePresenter(): BooksPresenter =
        Toothpick.openScopes(DI.APP_SCOPE, DI.BOOKS_ACTIVITY_SCOPE)
            .apply {
                val category = intent.getParcelableExtra<Category>(CategoriesActivity.ARG_CATEGORY)
                installModules(BooksActivityModule(category))
            }.getInstance(BooksPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = Toothpick.openScopes(DI.APP_SCOPE, DI.BOOKS_ACTIVITY_SCOPE)
        Toothpick.inject(this@BooksActivity, scope)
        layout.setContentView(this)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        layout.recyclerView.adapter = adapter
        layout.recyclerView.layoutManager = layoutManager
        adapter.setOnBookClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) Toothpick.closeScope(DI.BOOKS_ACTIVITY_SCOPE)
    }

    override fun showTitle(title: String) {
        this.title = title
    }

    override fun showBooks(books: List<Book>) {
        adapter.submitList(books)
    }

    override fun showEmptyScreen() {
        layout.emptyView.visibility = View.VISIBLE
    }

    override fun showProgressVisible(visible: Boolean) {
        layout.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onBookClick(book: Book) {
        startActivity<DetailsActivity>(ARG_BOOK to book)
    }

    companion object {
        const val ARG_BOOK = "ARG_BOOK"
    }
}

