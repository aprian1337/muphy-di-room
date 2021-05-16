package com.aprian1337.movie_catalogue.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.aprian1337.movie_catalogue.R
import com.aprian1337.movie_catalogue.utils.EspressoIdlingResource
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.*
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @get: Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test1LoadAllViewMenu() {
        onView(withId(R.id.nav_movie)).perform(click())
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.nav_profile)).perform(click())
    }

    @Test
    fun test2LoadMovies() {
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.tv_header_featured_movies)).perform(swipeUp())
        onView(withId(R.id.rv_list_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun test3LoadTvShow() {
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.tv_header_featured_tvshows)).perform(swipeUp())
        onView(withId(R.id.rv_list_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_list_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun test4DetailDataFromFeaturedMovies() {
        val position = 5
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
        checkDetail()
    }

    @Test
    fun test5DetailDataFromListMovies() {
        val position = 4
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
            checkDetail()
        }
    }

    @Test
    fun test6DetailDataFromFeaturedTvShow() {
        val position = 15
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
        checkDetail()
    }

    @Test
    fun test7DetailDataFromListTvShow() {
        val position = 14
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
            checkDetail()
        }
    }

    @Test
    fun test8AddFavorite(){
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.rv_featured_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.rv_featured_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_featured_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.btn_fav)).perform(click())
    }

    @Test
    fun test9LoadAndRemoveFavorites(){
        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetail()
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetail()
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetail()
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        checkDetail()
        onView(withId(R.id.btn_fav)).perform(click())
        pressBack()
    }

    private fun checkDetail() {
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_length)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(not(withText(""))))
        onView(withId(R.id.tv_detail_parental)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_parental)).check(matches(not(withText(""))))
        onView(withId(R.id.imgDetailView)).check(matches(isDisplayed()))
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(
                isDisplayed(),
                isAssignableFrom(TabLayout::class.java)
            )

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}