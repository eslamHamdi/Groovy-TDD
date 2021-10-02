package petros.efthymiou.groovy.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.domain.PlayList

class FakePlayListRepository:DataSource {
    override fun getPlayLists(): Flow<Result<List<PlayList>>> {

        return flow {
            emit(Result.success(listOf()))
        }





    }
}