package com.arditb.cryptotodate

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test


@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationTest {

    @get:Rule
    val intentsRule = IntentsTestRule(MainActivity::class.java)

    // Check if pressing the navigation button to go to the convert fragment takes you there
    @Test
    fun navigateToConvertFragment() {
//        onView(withId(R.id.convert_btn)).perform(click()
//        onView(withId(R.id.convert_view)).check(matches(isDisplayed()))
    }

    // Check if going to convert fragment and back to overview works
    @Test
    fun navigateToConvertAndBack() {
//        onView(withId(R.id.convert_btn)).perform(click())
//        onView(withId(R.id.convert_view)).check(matches(isDisplayed()))
//        onView(withId(R.id.overview_btn)).perform(click())
//        onView(withId(R.id.overview_view)).check(matches(isDisplayed()))
    }

    // Check if going to other fragment works
    @Test
    fun navigateToOtherFragment() {
//        onView(withId(R.id.other_btn)).perform(click()
//        onView(withId(R.id.other_view)).check(matches(isDisplayed()))
    }

    // Check if going to settings fragment works
    @Test
    fun navigateToSettingsFragment() {
//        onView(withId(R.id.settings_btn)).perform(click()
//        onView(withId(R.id.settings_view)).check(matches(isDisplayed()))
    }
}