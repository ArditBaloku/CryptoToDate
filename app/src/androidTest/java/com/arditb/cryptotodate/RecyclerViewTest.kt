package com.arditb.cryptotodate

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RecyclerViewTest {

    @get:Rule
    val intentsRule = IntentsTestRule(MainActivity::class.java)

    // Check if bitcoin is in the list
    @Test
    fun checkBitcoinInRecyclerView() {
        onView(withId(R.id.crypto_item_list))
            .check(ViewAssertions.matches(hasDescendant(withText("Bitcoin"))))
    }

    @Test
    fun clickOnBitcoin() {
        onView(withId(R.id.crypto_item_list))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText("Bitcoin")), click()))
        onView(withId(R.id.detail_layout)).check(matches(isDisplayed()))
    }
}