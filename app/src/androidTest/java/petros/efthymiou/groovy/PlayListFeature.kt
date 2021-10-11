package petros.efthymiou.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import petros.efthymiou.groovy.ui.MainActivity
import petros.nthChildOf


@RunWith(AndroidJUnit4::class)
@InternalCoroutinesApi
//@HiltAndroidTest
class PlayListFeature {

    //@Rule val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @InternalCoroutinesApi
    val mActivity = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Before
    fun initialize()
    {
       // hiltRule.inject()
    }
    @Test
    fun displayScreenTitle() {
        assertDisplayed("PlayLists")
    }



    @Test
    fun checkIfListIsDisplayed()
    {
        Thread.sleep(4000)
       assertRecyclerViewItemCount(R.id.playList_recycler,10)

        onView(allOf(withId(R.id.playList_name), isDescendantOfA(nthChildOf(withId(R.id.playList_recycler),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playList_category), isDescendantOfA(nthChildOf(withId(R.id.playList_recycler),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playList_image), isDescendantOfA(nthChildOf(withId(R.id.playList_recycler),0))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }
}