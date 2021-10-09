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
import org.mockito.kotlin.*
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import java.lang.Exception
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlayListServiceShould
{
    private var playListService:PlayListService? = null
    private var remoteService:RemoteService? = null
    private val playList = mock<List<PlayListsRemoteItem>>()

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
        playListService?.fetchPlayList()?.first()

       verify(remoteService, times(1))?.getList()
    }


    @Test
    fun wrapTheFetchedListIntoResult() = runBlockingTest {

        whenever(remoteService?.getList()).thenReturn(playList)

        val result = playListService?.fetchPlayList()?.first()


        assertThat(Result.Success(playList)).isEqualTo(result)

    }


    @Test
    fun wrapTheErrorIntoResult() = runBlockingTest {

        //also we can use thenThrow(RunTimeException)
        whenever(remoteService?.getList()).thenThrow(RuntimeException(""))

        val result:Result<List<PlayList>> = playListService?.fetchPlayList()?.first()!!


        assertThat(Result.Error("Fetching List Failed!!")).isEqualTo(result)

    }


}
