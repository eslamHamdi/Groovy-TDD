package petros.efthymiou.groovy.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.remote.PlayListService
import petros.efthymiou.groovy.remote.RemoteService
import petros.efthymiou.groovy.repositories.PlayListRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val httpClient: OkHttpClient = OkHttpClient()
val okHttp3IdlingResource = OkHttp3IdlingResource.create("NetworkIdelling", httpClient)


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:2999")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun providesPlayListService(remoteService: RemoteService): PlayListService {
        return PlayListService(remoteService)
    }

    @Provides
    @Singleton
    fun provideRepository(service: PlayListService): DataSource {
        return PlayListRepository(service)
    }




}