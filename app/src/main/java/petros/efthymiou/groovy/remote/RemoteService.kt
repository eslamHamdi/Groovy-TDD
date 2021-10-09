package petros.efthymiou.groovy.remote

import retrofit2.http.GET

interface RemoteService {

    @GET("/playlists")
    suspend fun getList():List<PlayListsRemoteItem?>?

}
