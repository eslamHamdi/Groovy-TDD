package petros.efthymiou.groovy.domain

import kotlinx.coroutines.flow.Flow

interface DataSource {


   suspend fun getPlayLists(): Flow<Result<List<PlayList>>>
   suspend fun getListDetails(id:String): Flow<Result<DomainListDetails>>
}