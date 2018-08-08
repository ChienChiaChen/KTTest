package com.chiachen.ktmvpdome.models.managers

import com.chiachen.ktmvpdome.netowrk.MovieSearchService
import com.mvp.moviedbapi.constants.Urls
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jianjiacheng on 2018/8/8.
 */
class HttpManager private constructor() {

    var movieSearchService: MovieSearchService

    init {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.MOVIEDB_BASE_URL)
                .build()

        movieSearchService = retrofit.create(MovieSearchService::class.java)
    }

    companion object {
        @Volatile private var sHttpManager: HttpManager? = null
        val instance: HttpManager
            get() {
                synchronized(HttpManager::class.java) {
                    if (null == sHttpManager) {
                        sHttpManager = HttpManager()
                    }
                    return sHttpManager!!
                }
            }
    }
}