package com.aprian1337.movie_catalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.models.MovieTv
import com.aprian1337.movie_catalogue.utils.DummyData
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
        onView(withId(R.id.rv_list_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                DummyData.getTvShows().size
            )
        )
    }

    @Test
    fun detailDataFromFeaturedMovies() {
        val data = DummyData.getFeaturedMovies()[5]
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )
        checkDetail(data)
    }

    @Test
    fun detailDataFromListMovies() {
        val data = DummyData.getMovies()[0]
        onView(withId(R.id.rv_list_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetail(data)
    }

    @Test
    fun detailDataFromFeaturedTvShow() {
        val data = DummyData.getFeaturedTvShows()[5]
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        checkDetail(data)
    }

    @Test
    fun detailDataFromListTvShow(){
        val data = DummyData.getTvShows()[0]
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_list_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail(data)
    }

    private fun checkDetail(data : MovieTv) {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_title)).check(matches(withText(data.title)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_genre)).check(matches(withText(data.genre)))
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_length)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_length)).check(matches(withText(data.length)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_overview)).check(matches(withText(data.overview)))
        onView(withId(R.id.tv_parental)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_parental)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_parental)).check(matches(withText(data.parental)))
        onView(withId(R.id.imgView)).check(matches(isDisplayed()))
    }
}