package com.chiachen.ktmvpdome.testutil

import com.chiachen.ktmvpdome.utils.SchedulerProvider
import rx.schedulers.TestScheduler

/**
 * Created by jianjiacheng on 2018/8/8.
 */
class TestSchedulerProvider constructor(private val testScheduler: TestScheduler)  : SchedulerProvider {
    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}