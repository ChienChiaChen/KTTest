package com.chiachen.ktmvpdome.presenters

import android.view.View
import com.chiachen.ktmvpdome.R
import com.chiachen.ktmvpdome.interfaces.MainActivityContract
import com.chiachen.ktmvpdome.models.managers.HttpManager
import com.chiachen.ktmvpdome.utils.SchedulerProvider
import com.mvp.moviedbapi.constants.Urls
import com.mvp.moviedbapi.models.apis.SearchResults
import rx.Subscriber

/**
 * Created by jianjiacheng on 2018/8/8.
 */

class MainActivityPresenter(private val schedulers: SchedulerProvider) : MainActivityContract.Presenter {

    var mView: MainActivityContract.MainActivityView? = null

    val TAG = "MainActivityPresenter"

    override fun attach(view: MainActivityContract.MainActivityView) {
        this.mView = view
    }

    override fun detach() {
        this.mView = null
    }

    override fun searchMovie(text: String, page: Int) {
        if (mView != null && text.isEmpty()) {
            mView?.showToast(R.string.search_error_no_text)
        }

        val result = HttpManager.instance.movieSearchService.getMovies(Urls.MOVIEDB_API_KEY_VALUE, text, page)

        result.subscribeOn(schedulers.ioScheduler())
                .observeOn(schedulers.uiScheduler())
                .subscribe(object : Subscriber<SearchResults>() {
                    override fun onError(e: Throwable?) {
//                        Log.e(TAG, "OnError" + e?.message)
                        mView?.showToast(R.string.search_error_text)
                    }

                    override fun onCompleted() {
//                        Log.e(TAG, "onCompleted")
                    }

                    override fun onNext(searchResults: SearchResults) {
//                        Log.e(TAG, "onNext" + searchResults.results?.get(0)?.originalTitle)
                        mView?.updateMovieAdapter(searchResults)
                        val nextButtonGone = searchResults.totalPages!! < 2 || searchResults.page == searchResults.totalPages
                        mView?.setUpOnNextPageButton(text, if (nextButtonGone) View.GONE else View.VISIBLE, searchResults.page!! + 1)
                    }
                })
    }
}