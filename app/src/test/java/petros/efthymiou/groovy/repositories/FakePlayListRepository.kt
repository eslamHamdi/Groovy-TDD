package petros.efthymiou.groovy.repositories

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mockito.kotlin.mock
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.domain.DomainListDetails
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result

class FakePlayListRepository:DataSource {


    val playList = mock<List<PlayList>>()
    val detailList = mock<DomainListDetails>()


    var failure = false

    fun setError(state: Boolean) {
        failure = state
    }

    override suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {


        return flow {

            if (!failure) {
                emit(Result.Success(playList))
            } else {
                emit(Result.Error("Failed"))
            }

        }


    }

    override suspend fun getListDetails(id:String): Flow<Result<DomainListDetails>>{
        return flow {

            if (!failure) {
                emit(Result.Success(detailList))
            } else {
                emit(Result.Error("Failed"))
            }

        }
    }
}