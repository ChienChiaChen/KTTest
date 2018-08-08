package com.chiachen.ktmvpdome.presenters

import com.chiachen.ktmvpdome.R
import com.chiachen.ktmvpdome.interfaces.MainActivityContract
import com.chiachen.ktmvpdome.netowrk.MovieSearchService
import com.chiachen.ktmvpdome.testutil.TestSchedulerProvider
import com.mvp.moviedbapi.models.apis.SearchResults
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.TestScheduler

/**
 * Created by jianjiacheng on 2018/8/8.
 */

//@RunWith(AndroidJUnit4::class)
class MainActivityPresenterTest {

    companion object {
        @JvmStatic
        val SEARCH = "star wars"
    }

    @Mock
    lateinit var mockView: MainActivityContract.MainActivityView
    lateinit var testSchedulerProvider: TestSchedulerProvider
    lateinit var mPresenter: MainActivityPresenter
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        testSchedulerProvider = TestSchedulerProvider(testScheduler)
        mPresenter = MainActivityPresenter(testSchedulerProvider)
        mPresenter.attach(mockView)
    }

    @Test
    fun test_movie_null_text_error() {
        mPresenter.searchMovie("", 1)
        Mockito.verify(mockView).showToast(R.string.search_error_no_text)
    }

    @Test
    fun test_search_movie_error() {
        val mockedResponse: Throwable = mock()
        var movieSearchService: MovieSearchService = mock()
        Mockito.doReturn(Observable.just(mockedResponse))
                .`when`(movieSearchService)
                .getMovies(anyString(), anyString(), anyInt())

        mPresenter.searchMovie(SEARCH, 1)
        testScheduler.triggerActions()
        verify(mockView, Mockito.atLeastOnce()).showToast(R.string.search_error_text)
    }

    @Test
    fun test_search_movie_successful() {
        val mockedResponse: SearchResults = mock()
        var movieSearchService: MovieSearchService = mock()
        Mockito.doReturn(Observable.just(mockedResponse))
                .`when`(movieSearchService)
                .getMovies(anyString(), anyString(), anyInt())

        mPresenter.searchMovie(SEARCH, 1)
        testScheduler.triggerActions()
        Mockito.verify(mockView, Mockito.atLeastOnce()).updateMovieAdapter(any())
    }
}