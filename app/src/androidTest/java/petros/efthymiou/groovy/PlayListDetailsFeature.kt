package petros.efthymiou.groovy

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.mockito.internal.util.StringUtil
import petros.efthymiou.groovy.ui.MainActivity
import petros.efthymiou.groovy.utils.DataBindingIdlingResource
import petros.efthymiou.groovy.utils.EspressoIdlingResource
import petros.efthymiou.groovy.utils.monitorActivity

@RunWith(AndroidJUnit4::class)
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
class PlayListDetailsFeature {


    private lateinit var activityScenario: ActivityScenario<MainActivity>


    private val dataBindingIdlingResource = DataBindingIdlingResource()


    @Before
    fun initialize()
    {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)






    }

    @After
    fun tearDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)

    }



    @Test
    fun displayScreenTitle()   {

        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        clickListItem(R.id.playList_recycler, 0)

        assertDisplayed("PlayListDetails")
        activityScenario.close()
    }

    @Test
    fun displayListDetails()   {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        clickListItem(R.id.playList_recycler, 0)

        assertDisplayed("Hard Rock Cafe")
        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")
        activityScenario.close()
    }

}