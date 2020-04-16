package com.arditb.cryptotodate

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
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
        onView(withId(R.id.converterFragment)).perform(click())
        onView(withId(R.id.converter_layout)).check(matches(isDisplayed()))
    }

    // Check if going to convert fragment and back to overview works
    @Test
    fun navigateToConvertAndBack() {
        onView(withId(R.id.converterFragment)).perform(click())
        onView(withId(R.id.converter_layout)).check(matches(isDisplayed()))
        // First back is to remove the keyboard
        pressBack()
        // Second back is to go back to the previous fragment
        pressBack()
        onView(withId(R.id.overview_layout)).check(matches(isDisplayed()))
    }

    // Check if going to other fragment works
    @Test
    fun navigateToOtherFragment() {
        onView(withId(R.id.otherFragment)).perform(click())
        onView(withId(R.id.other_layout)).check(matches(isDisplayed()))
    }

    // Check if going to other fragment and back works
    @Test
    fun navigateToOtherAndBack() {
        onView(withId(R.id.otherFragment)).perform(click())
        onView(withId(R.id.other_layout)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.overview_layout)).check(matches(isDisplayed()))
    }

    // Check if going to settings fragment works
    @Test
    fun navigateToSettingsFragment() {
        onView(withId(R.id.settingsFragment)).perform(click())
        onView(withId(R.id.settings_layout)).check(matches(isDisplayed()))
    }

    // Check if going to settings fragment and back to overview works
    @Test
    fun navigateToSettingsAndBack() {
        onView(withId(R.id.settingsFragment)).perform(click())
        onView(withId(R.id.settings_layout)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.overview_layout)).check(matches(isDisplayed()))
    }
}