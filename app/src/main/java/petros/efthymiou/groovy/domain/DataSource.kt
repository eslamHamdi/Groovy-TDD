package petros.efthymiou.groovy.domain

import kotlinx.coroutines.flow.Flow

interface DataSource {


    fun getPlayLists(): Flow<Result<List<PlayList>>>
}