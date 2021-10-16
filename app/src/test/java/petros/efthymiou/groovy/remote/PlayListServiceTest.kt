package petros.efthymiou.groovy.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.takeWhile
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

         playListService?.fetchPlayList()?.takeWhile {
            it is Result.Success
        }?.collect {
            assertThat(it).isEqualTo(Result.Success(playList.toDomain()))
        }




    }


    @Test
    fun wrapTheErrorIntoResult() = runBlockingTest {

        whenever(remoteService?.getList()).thenThrow(RuntimeException(""))

        val result:Result<List<PlayList>> = playListService?.fetchPlayList()?.first()!!


        assertThat(Result.Error("Fetching List Failed!!")).isEqualTo(result)

    }


    @Test
    fun emitLoadingStatus()= runBlockingTest {

        playListService?.fetchPlayList()?.takeWhile {
            it is Result.Loading
        }?.collect {
            assertThat(it).isEqualTo(Result.Loading<List<PlayList>>())
        }


    }


}
