package com.chiachen.ktmvpdome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.chiachen.ktmvpdome.adapters.MovieSearchAdapter
import com.chiachen.ktmvpdome.interfaces.MainActivityContract
import com.chiachen.ktmvpdome.presenters.MainActivityPresenter
import com.mvp.moviedbapi.models.apis.SearchResults
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {

    private var mPresenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.attach(this)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        searchButton.setOnClickListener { mPresenter.searchMovie(edittext.text.toString(), 1) }
    }

    override fun showToast(idString: Int) {
        Toast.makeText(this, idString, Toast.LENGTH_LONG).show()
    }

    override fun setUpOnNextPageButton(text: String, visibility: Int, page: Int) {
        nextButton.visibility = visibility
        nextButton.setOnClickListener { mPresenter.searchMovie(text, page) }
    }

    override fun updateMovieAdapter(searchResults: SearchResults) {
        if (recyclerView.adapter is MovieSearchAdapter) {
            val movieSearchAdapter = recyclerView.adapter as MovieSearchAdapter
            movieSearchAdapter.setSearchResults(searchResults)
            movieSearchAdapter.notifyDataSetChanged()
        } else {
            recyclerView.adapter = MovieSearchAdapter(searchResults)
        }
    }

    override fun onDestroy() {
        mPresenter.detach()
        super.onDestroy()
    }
}
