package com.chiachen.ktmvpdome.interfaces

import android.support.annotation.StringRes
import com.mvp.moviedbapi.models.apis.SearchResults

/**
 * Created by jianjiacheng on 2018/8/8.
 */
interface MainActivityContract {
    interface MainActivityView {
        fun showToast(@StringRes idString: Int)
        fun setUpOnNextPageButton(text: String, visibility: Int, page: Int)
        fun updateMovieAdapter(searchResults: SearchResults)
    }

    interface Presenter : BasePresenter<MainActivityView> {
        fun searchMovie(text: String, page: Int)
    }
}