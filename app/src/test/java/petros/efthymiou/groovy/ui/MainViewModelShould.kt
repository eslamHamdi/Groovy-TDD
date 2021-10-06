package petros.efthymiou.groovy.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.repositories.FakePlayListRepository
import petros.efthymiou.groovy.ui.fragments.MainViewModel
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainViewModelShould {


    private var viewModel:MainViewModel?=null
    private var repository: FakePlayListRepository?=null


    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @Before
    fun initializeViewModel()
    {
        repository = FakePlayListRepository()
        viewModel = MainViewModel(repository!!as DataSource)

    }
    @After
    fun clean()
    {
        viewModel = null
        repository = null

    }

    @Test
    fun getPlayListsFromRepository() = runBlockingTest {

        //mocking repository to make use of verify making sure that getPlayLists() invoked
        val repository = mock(FakePlayListRepository::class.java)
        viewModel = MainViewModel(repository)
        viewModel!!.getPlayLists()

        verify(repository, times(1))?.getPlayLists()

    }

    @Test
    fun emitTheCorrectPlayList()= runBlockingTest {


        viewModel?.getPlayLists()
        assertThat(viewModel?.playList?.getValueForTest()).isEqualTo(repository?.playList)
    }


    @Test
    fun emitErrorIfGettingListIsFailed() = runBlockingTest{


        repository?.setError(true)

        var expected ="Failed"

        viewModel?.getPlayLists()

        assertThat(viewModel?.errorState?.getValueForTest()).isEqualTo(expected)



        }





    }


