package petros.efthymiou.groovy.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule

@ExperimentalCoroutinesApi
class PlayListServiceShould
{
    private var playListService:PlayListService? = null
    private var remoteService:RemoteService? = null
    private val playList = mock<List<PlayList>>()

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    @Before
    fun initialization()
    {
        remoteService = mock(RemoteService::class.java)
        playListService = PlayListService(remoteService!!)
    }

    @After
    fun tearDown()
    {
        remoteService = null
        playListService = null
    }


    @Test
    fun getPlayListsFromRemoteService() = runBlockingTest {
        playListService?.fetchPlayList()

       verify(remoteService, times(1))?.getList()
    }


    @Test
    fun wrapTheFetchedListIntoResult() = runBlockingTest {

        whenever(remoteService?.getList()).thenReturn(playList)

        val result = playListService?.fetchPlayList()?.first() as Result.Success


        assertThat(playList).isEqualTo(result.data)

    }


    @Test
    fun wrapTheErrorIntoResult() = runBlockingTest {

        whenever(remoteService?.getList()).thenThrow(Exception("Failed To Get The List"))

        val result = playListService?.fetchPlayList()?.first() as Result.Error


        assertThat("Failed To Get The List").isEqualTo(result.message)

    }


}
