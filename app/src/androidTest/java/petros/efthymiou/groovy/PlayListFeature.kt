package petros.efthymiou.groovy


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaProgressBarAssertions.assertProgress
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import petros.efthymiou.groovy.di.okHttp3IdlingResource
import petros.efthymiou.groovy.ui.MainActivity
import petros.efthymiou.groovy.utils.DataBindingIdlingResource
import petros.efthymiou.groovy.utils.EspressoIdlingResource
import petros.efthymiou.groovy.utils.monitorActivity
import petros.nthChildOf


@RunWith(AndroidJUnit4::class)
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
class PlayListFeature {


    private lateinit var activityScenario: ActivityScenario<MainActivity>


    private val dataBindingIdlingResource = DataBindingIdlingResource()


    @Before
    fun initialize() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)


        IdlingRegistry.getInstance().register(dataBindingIdlingResource)

        IdlingRegistry.getInstance().register(okHttp3IdlingResource)


    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)

    }

    @Test
    fun displayScreenTitle() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        assertDisplayed("PlayLists")

        activityScenario.close()
    }


    @Test
    fun check_2_IfListIsDisplayed() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        dataBindingIdlingResource.monitorActivity(activityScenario)
        assertRecyclerViewItemCount(R.id.playList_recycler, 10)

        onView(
            allOf(
                withId(R.id.playList_name),
                isDescendantOfA(nthChildOf(withId(R.id.playList_recycler), 0))
            )
        )
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playList_category),
                isDescendantOfA(nthChildOf(withId(R.id.playList_recycler), 0))
            )
        )
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playList_image),
                isDescendantOfA(nthChildOf(withId(R.id.playList_recycler), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun check_1_IfLoaderIsDisplayedDuringFetching() = runBlocking {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)

        assertDisplayed(R.id.progress_bar)
        Thread.sleep(3000)

        activityScenario.close()


    }

    @Test
    fun check_3_IfLoaderHidesAfterFetchingFinishes() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        assertNotDisplayed(R.id.progress_bar)
        activityScenario.close()
    }

    @Test
    fun check_4_IfRockPlayListsHaveTheRockCategoryIcon() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        onView(
            allOf(
                withId(R.id.playList_image),
                isDescendantOfA(nthChildOf(withId(R.id.playList_recycler), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))


        onView(
            allOf(
                withId(R.id.playList_image),
                isDescendantOfA(nthChildOf(withId(R.id.playList_recycler), 3))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
        activityScenario.close()

    }


}