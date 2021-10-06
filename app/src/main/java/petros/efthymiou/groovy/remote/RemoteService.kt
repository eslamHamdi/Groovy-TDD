package petros.efthymiou.groovy.remote

import petros.efthymiou.groovy.domain.PlayList
import retrofit2.http.GET

interface RemoteService {

    @GET("playlists")
    fun getList():List<PlayList>

}
