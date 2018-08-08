package com.chiachen.ktmvpdome.utils

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Tamas_Kozmer on 8/4/2017.
 */
class AppSchedulerProvider : SchedulerProvider {

    override fun ioScheduler() = Schedulers.io()
    override fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}