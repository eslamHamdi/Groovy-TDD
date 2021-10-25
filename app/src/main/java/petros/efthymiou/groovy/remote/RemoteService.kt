package petros.efthymiou.groovy.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET("/playlists")
    suspend fun getList(): List<PlayListsRemoteItem?>?

    @GET("/playlists-details/{id}")
    suspend fun getdetails(@Path("id") id: String): ListDetails

}
