package com.melkopisi.dailyforcast.features

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.melkopisi.dailyforcast.R
import org.junit.Rule
import org.junit.Test

class ForecastListFragmentTest {

  @get:Rule
  val activityRule = ActivityScenarioRule(MainActivity::class.java)

  @Test
  fun when_search_recyclerView_appears() {
    Espresso.onView(ViewMatchers.withId(R.id.search))
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    Espresso.onView(ViewMatchers.withId(R.id.search))
      .perform(ViewActions.typeText("cairo"), ViewActions.pressImeActionButton())
    Espresso.onView(ViewMatchers.withId(R.id.forecast_recyclerview))
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }

  @Test
  fun when_search_progress_start_loading() {
    Espresso.onView(ViewMatchers.withId(R.id.progressBar))
      .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(GONE)))

    Espresso.onView(ViewMatchers.withId(R.id.search))
      .perform(ViewActions.typeText("cairo"), ViewActions.pressImeActionButton())
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

  }

}