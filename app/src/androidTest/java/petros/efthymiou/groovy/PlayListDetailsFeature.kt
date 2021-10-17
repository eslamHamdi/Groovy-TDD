package petros.efthymiou.groovy

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.adevinta.android.barista.internal.performActionOnView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import petros.efthymiou.groovy.ui.MainActivity
import petros.efthymiou.groovy.utils.DataBindingIdlingResource
import petros.efthymiou.groovy.utils.EspressoIdlingResource
import petros.efthymiou.groovy.utils.monitorActivity
import petros.nthChildOf

@RunWith(AndroidJUnit4::class)
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@LargeTest
class PlayListDetailsFeature {


    private lateinit var activityScenario: ActivityScenario<MainActivity>


    private val dataBindingIdlingResource = DataBindingIdlingResource()


    @Before
    fun initialize()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        IdlingRegistry.getInstance().register(dataBindingIdlingResource)



    }

    @After
    fun tearDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        activityScenario.close()
    }

    @Test
    fun displayScreenTitle()   {
        dataBindingIdlingResource.monitorActivity(activityScenario)

        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playList_container),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.playList_recycler),
                        0
                    )
                )
            )
        )
            .perform(click())
        BaristaVisibilityAssertions.assertDisplayed("PlayListDetails")
    }

}