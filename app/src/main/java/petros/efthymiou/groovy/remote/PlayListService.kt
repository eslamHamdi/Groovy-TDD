package petros.efthymiou.groovy.remote


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import javax.inject.Inject

class PlayListService @Inject constructor(private val remoteService:RemoteService){

    suspend fun fetchPlayList(): Flow<Result<List<PlayList>>> {

         return try {

             Log.e(null, "fetchPlayList: success", )

             val list = remoteService.getList().toDomain()
             flow {

                 emit(Result.Success(list))
             }

         }catch(e:Exception) {
             Log.e(null, "fetchPlayList: ${e.message}", )
             flow {
                 emit(Result.Error("Fetching List Failed!!"))
             }
         }

     }

}
