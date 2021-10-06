package petros.efthymiou.groovy.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import petros.efthymiou.groovy.remote.PlayListService
import javax.inject.Inject

class PlayListRepository @Inject constructor(private val service: PlayListService) :DataSource  {
    override fun getPlayLists(): Flow<Result<List<PlayList>>> {


        return service.fetchPlayList()
    }
}