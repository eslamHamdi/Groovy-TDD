package petros.efthymiou.groovy.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import petros.efthymiou.groovy.remote.PlayListService
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule


@ExperimentalCoroutinesApi
class PlayListRepositoryShould
{

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

     var repository: PlayListRepository? = null
     var service: PlayListService? = null

    @Before
    fun initializeRepository()
    {
        service = mock()
        repository = PlayListRepository(service!!)

    }

    @After
    fun tearDown()
    {
        repository = null
        service = null
    }

    @Test
    fun invokeServiceFetchPlayList()
    {
        repository?.getPlayLists()

        verify(service, times(1))?.fetchPlayList()
    }
}