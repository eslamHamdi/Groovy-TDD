package petros.efthymiou.groovy.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.domain.Result
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
    fun getPlayListsFromRepository() = coroutineTestRule.runBlockingTest {

        //mocking repository to make use of verify making sure that getPlayLists() invoked
        val repository = mock(FakePlayListRepository::class.java)
        viewModel = MainViewModel(repository)
        viewModel!!.getPlayLists()

        verify(repository, times(1))?.getPlayLists()

    }

    @Test
    fun emitTheCorrectPlayList()= coroutineTestRule.runBlockingTest {


        viewModel?.getPlayLists()
        assertThat(viewModel?.playList?.getValueForTest()).isEqualTo(repository?.playList)
    }


    @Test
    fun emitErrorIfGettingListIsFailed() = coroutineTestRule.runBlockingTest{


        repository?.setError(true)

        val expected ="Failed"

        viewModel?.getPlayLists()

        assertThat(viewModel?.errorState?.getValueForTest()).isEqualTo(expected)



        }


    @Test
    fun showLoaderWhileGettigList()= coroutineTestRule.runBlockingTest {

        mockLoadingCase()

        assertThat(viewModel!!.progressLiveData.getValueForTest()).isTrue()
    }



    @Test
    fun hideLoaderWhenFetchingEnds()= coroutineTestRule.runBlockingTest {

        mockSuccessCase()

        assertThat(viewModel!!.progressLiveData.getValueForTest()).isFalse()
    }



    @Test
    fun hideLoaderWhenFetchingFails()= coroutineTestRule.runBlockingTest {

        mockErrorCase()

        assertThat(viewModel!!.progressLiveData.getValueForTest()).isFalse()
    }

    @Test
    fun getPlayListDetails() = runBlockingTest {
        val repository = mock(FakePlayListRepository::class.java)
        viewModel = MainViewModel(repository)
        val id = "1"

        viewModel?.getPlayListsDetails(id)

        verify(repository, times(1))?.getListDetails(id)
    }

    @Test
    fun emitTheCorrectPlayListDetails()= coroutineTestRule.runBlockingTest {


        viewModel?.getPlayListsDetails("1")
        assertThat(viewModel?.playListDetails?.getValueForTest()).isEqualTo(repository?.detailList)
    }


    @Test
    fun emitErrorIfGettingListDetailsIsFailed() = coroutineTestRule.runBlockingTest{


        repository?.setError(true)

        val expected ="Failed"

        viewModel?.getPlayListsDetails("1")

        assertThat(viewModel?.errorState?.getValueForTest()).isEqualTo(expected)



    }



    private suspend fun mockLoadingCase() {
        val repository = mock(FakePlayListRepository::class.java)

        whenever(repository.getPlayLists()).thenReturn(flowOf(Result.Loading()))
        viewModel = MainViewModel(repository)
        viewModel!!.getPlayLists()
    }

    private suspend fun mockSuccessCase() {
        val repository = mock(FakePlayListRepository::class.java)

        whenever(repository.getPlayLists()).thenReturn(flowOf(Result.Success(listOf())))
        viewModel = MainViewModel(repository)
        viewModel!!.getPlayLists()
    }

    private suspend fun mockErrorCase() {
        val repository = mock(FakePlayListRepository::class.java)

        whenever(repository.getPlayLists()).thenReturn(flowOf(Result.Error("failed!!")))
        viewModel = MainViewModel(repository)
        viewModel!!.getPlayLists()
    }
    }





