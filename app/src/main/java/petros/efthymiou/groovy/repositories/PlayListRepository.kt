package petros.efthymiou.groovy.repositories

import kotlinx.coroutines.flow.Flow
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.domain.PlayList
import javax.inject.Inject

class PlayListRepository @Inject constructor() :DataSource  {
    override fun getPlayLists(): Flow<Result<List<PlayList>>> {
        TODO("Not yet implemented")
    }
}