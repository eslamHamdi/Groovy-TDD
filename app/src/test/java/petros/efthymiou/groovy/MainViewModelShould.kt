package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
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
        repository = mock(FakePlayListRepository::class.java)
        viewModel = MainViewModel(repository!! as DataSource)

    }
    @After
    fun clean()
    {
        viewModel = null
        repository = null

    }

    @Test
    fun getPlayListsFromRepository() = runBlockingTest {

        viewModel?.playList?.getValueForTest()
        verify(repository, times(1))?.getPlayLists()
    }


}