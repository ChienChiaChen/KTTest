package com.chiachen.ktmvpdome.utils

import rx.Scheduler

/**
 * Created by jianjiacheng on 2018/8/8.
 */

interface SchedulerProvider {
    fun uiScheduler():Scheduler
    fun ioScheduler():Scheduler
}