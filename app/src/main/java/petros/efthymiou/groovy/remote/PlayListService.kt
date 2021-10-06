package petros.efthymiou.groovy.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import javax.inject.Inject

class PlayListService @Inject constructor(private val remoteService:RemoteService){

     fun fetchPlayList(): Flow<Result<List<PlayList>>> {

         val list = remoteService.getList()

         return flow {

         }
     }

}
