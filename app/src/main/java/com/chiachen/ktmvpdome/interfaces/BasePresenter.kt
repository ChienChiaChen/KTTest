package com.chiachen.ktmvpdome.interfaces

/**
 * Created by jianjiacheng on 2018/8/8.
 */

interface BasePresenter<in T> {// in 代表消費者 (extends
    fun attach(view: T)
    fun detach()
}