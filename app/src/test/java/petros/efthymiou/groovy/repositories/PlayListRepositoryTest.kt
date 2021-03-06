package petros.efthymiou.groovy.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.domain.DomainListDetails
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import petros.efthymiou.groovy.remote.PlayListService
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule


@ExperimentalCoroutinesApi
class PlayListRepositoryShould
{

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

     private var repository: PlayListRepository? = null
     private var service: PlayListService? = null
    private val playList = mock<List<PlayList>>()
    private val listDetails = mock<DomainListDetails>()


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
    fun invokeServiceFetchPlayList()= coroutineTestRule.runBlockingTest {

        repository?.getPlayLists()

        verify(service, times(1))?.fetchPlayList()
    }


    @Test
    fun emitTheSameListObtainedFromService()= coroutineTestRule.runBlockingTest {
        whenever(service?.fetchPlayList()).thenReturn (
            flow{
                emit(Result.Success(playList))
            }
            )

        val result = repository?.getPlayLists()?.first() as Result.Success


        assertThat(playList).isEqualTo(result.data)
    }

    @Test
    fun propagateErrors()= coroutineTestRule.runBlockingTest {

        whenever(service?.fetchPlayList()).thenReturn (
            flow{
                emit(Result.Error("Failed!!"))
            }
        )

        val result = repository?.getPlayLists()?.first() as Result.Error

        assertThat("Failed!!").isEqualTo(result.message)


    }

    @Test
    fun propagateLoadingStatus()= coroutineTestRule.runBlockingTest {

        repository?.getPlayLists()?.takeWhile {
            it is Result.Loading
        }?.collect {
            assertThat(it).isEqualTo(Result.Loading<List<PlayList>>())
        }

    }

    @Test
    fun invokeServiceFetchPlayListDetails()= coroutineTestRule.runBlockingTest {

        repository?.getListDetails("1")

        verify(service, times(1))?.fetchPlayListDetails("1")
    }

    @Test
    fun emitTheSameListDetailsObtainedFromService()= coroutineTestRule.runBlockingTest {
        mockDetailSuccessCase()

        val result = repository?.getListDetails("1")?.first() as Result.Success


        assertThat(listDetails).isEqualTo(result.data)
    }



    @Test
    fun propagateDetailErrors()= coroutineTestRule.runBlockingTest {

        mockDetailErrorCase()

        val result = repository?.getListDetails("1")?.first() as Result.Error

        assertThat("Failed!!").isEqualTo(result.message)


    }

    private fun mockDetailErrorCase() {
        whenever(service?.fetchPlayListDetails("1")).thenReturn(
            flow {
                emit(Result.Error("Failed!!"))
            }
        )
    }

    private fun mockDetailSuccessCase() {
        whenever(service?.fetchPlayListDetails("1")).thenReturn(
            flow {
                emit(Result.Success(listDetails))
            }
        )
    }


}