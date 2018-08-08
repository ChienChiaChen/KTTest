package com.chiachen.ktmvpdome.netowrk

import com.mvp.moviedbapi.constants.Urls
import com.mvp.moviedbapi.models.apis.SearchResults
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by jianjiacheng on 2018/8/8.
 */

interface MovieSearchService {
    @GET("movie?")
    fun getMovies(@Query(Urls.MOVIEDB_API_KEY_QUERY) apiKey: String,
                  @Query(Urls.MOVIEDB_MOVIE_TITLE_QUERY) movieTitle: String,
                  @Query(Urls.MOVIEDB_PAGE_QUERY) page: Int
    ) : Observable<SearchResults>
}