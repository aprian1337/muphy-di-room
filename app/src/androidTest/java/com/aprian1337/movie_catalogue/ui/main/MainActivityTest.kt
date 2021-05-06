package com.aprian1337.movie_catalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.data.models.MovieTv
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get: Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadAllViewMenu() {
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.nav_profile)).perform(click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                DummyData.getFeaturedMovies().size
            )
        )
        onView(withId(R.id.tv_header_featured_movies)).perform(swipeUp())
        onView(withId(R.id.rv_list_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                DummyData.getMovies().size
            )
        )
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                DummyData.getFeaturedTvShows().size
            )
        )
        onView(withId(R.id.tv_header_featured_tvshows)).perform(swipeUp())
        onView(withId(R.id.rv_list_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                DummyData.getTvShows().size
            )
        )
    }

    @Test
    fun detailDataFromFeaturedMovies() {
        val position = 5
        val data = DummyData.getFeaturedMovies()[position]
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
        checkDetail(data)
    }

    @Test
    fun detailDataFromListMovies() {
        val position = 4
        val data = DummyData.getMovies()[position]
        try {
            for (i in 1..20) {
                onView(withId(R.id.tv_list_movies)).perform(swipeUp())
            }
        }catch (e: PerformException){ }finally {
            onView(withId(R.id.rv_list_movies)).check(matches(isDisplayed()))
            onView(withId(R.id.rv_list_movies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    click()
                )
            )
            checkDetail(data)
        }
    }

    @Test
    fun detailDataFromFeaturedTvShow() {
        val position = 5
        val data = DummyData.getFeaturedTvShows()[position]
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
        checkDetail(data)
    }

    @Test
    fun detailDataFromListTvShow() {
        val position = 14
        val data = DummyData.getTvShows()[position]
        onView(withId(R.id.nav_tvshow)).perform(click())
        try {
            for (i in 1..20) {
                onView(withId(R.id.tv_list_tv_shows)).perform(swipeUp())
            }
        }catch (e: PerformException){ }finally {
            onView(withId(R.id.rv_list_tv_shows)).check(matches(isDisplayed()))
            onView(withId(R.id.rv_list_tv_shows)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    click()
                )
            )
            checkDetail(data)
        }
    }

    private fun checkDetail(data: MovieTv) {
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(data.title)))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_genre)).check(matches(withText(data.genre)))
        onView(withId(R.id.tv_detail_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_length)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_length)).check(matches(withText(data.length)))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_overview)).check(matches(withText(data.overview)))
        onView(withId(R.id.tv_detail_parental)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_parental)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_parental)).check(matches(withText(data.parental)))
        onView(withId(R.id.imgDetailView)).check(matches(isDisplayed()))
    }
}