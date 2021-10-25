package petros.efthymiou.groovy.remote


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.domain.DomainListDetails
import petros.efthymiou.groovy.domain.PlayList
import petros.efthymiou.groovy.domain.Result
import petros.efthymiou.groovy.utils.wrapEspressoIdlingResource
import javax.inject.Inject

class PlayListService @Inject constructor(private val remoteService: RemoteService) {

    suspend fun fetchPlayList(): Flow<Result<List<PlayList>>> {


        return wrapEspressoIdlingResource {
            flow {

                val list = remoteService.getList().toDomain()
                emit(Result.Loading())
                emit(Result.Success(list))
            }.catch {
                emit(Result.Error("Fetching List Failed!!"))
            }
        }


    }

    fun fetchPlayListDetails(id: String): Flow<Result<DomainListDetails>> {

        return wrapEspressoIdlingResource {
            flow {
                val details = remoteService.getdetails(id).toDomain()
                emit(Result.Loading())
                emit(Result.Success(details))
            }.catch {
                emit(Result.Error(message = "fetching List Details Failed"))
            }
        }
    }
}



