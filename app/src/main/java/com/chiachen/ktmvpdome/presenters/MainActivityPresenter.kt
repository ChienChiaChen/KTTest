package com.chiachen.ktmvpdome.presenters

import com.chiachen.ktmvpdome.interfaces.MainActivityContract

/**
 * Created by jianjiacheng on 2018/8/8.
 */

class MainActivityPresenter : MainActivityContract.Presenter {

    var mView: MainActivityContract.MainActivityView? = null

    override fun attach(view: MainActivityContract.MainActivityView) {
        this.mView = view
    }

    override fun detach() {
        this.mView = null
    }

    override fun searchMovie(text: String, page: Int) {

    }
}