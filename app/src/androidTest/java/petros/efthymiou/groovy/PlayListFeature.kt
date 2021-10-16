package petros.efthymiou.groovy



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.junit.*
import org.junit.runner.OrderWith
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import petros.efthymiou.groovy.ui.MainActivity
import petros.efthymiou.groovy.utils.EspressoIdlingResource
import petros.nthChildOf


@RunWith(AndroidJUnit4::class)
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PlayListFeature {


private lateinit var activityScenario:ActivityScenario<MainActivity>



    //private val dataBindingIdlingResource = DataBindingIdlingResource()
    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()


    @Before
    fun initialize()
    {

         activityScenario = ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        //dataBindingIdlingResource.monitorActivity(activityScenario)

    }

    @After
    fun tearDown()
    {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
       // IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
        activityScenario.close()
    }

    @Test
    fun displayScreenTitle()   {
        assertDisplayed("PlayLists")
    }



    @Test
     fun check_2_IfListIsDisplayed() {


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

    @Test
    fun check_1_IfLoaderIsDisplayedDuringFetching(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)

        assertDisplayed(R.id.progress_bar)


    }

    @Test
   fun check_3_IfLoaderHidesAfterFetchingFinishes()
    {
       assertNotDisplayed(R.id.progress_bar)
   }


}