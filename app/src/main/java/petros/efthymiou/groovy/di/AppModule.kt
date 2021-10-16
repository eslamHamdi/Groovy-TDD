package petros.efthymiou.groovy.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.remote.PlayListService
import petros.efthymiou.groovy.remote.RemoteService
import petros.efthymiou.groovy.repositories.PlayListRepository
import petros.efthymiou.groovy.ui.fragments.MainViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit():Retrofit
    {

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:2999")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit):RemoteService
    {
        return retrofit.create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun providesPlayListService(remoteService: RemoteService): PlayListService
    {
        return PlayListService(remoteService)
    }

    @Provides
    @Singleton
    fun provideRepository(service: PlayListService): DataSource
    {
        return PlayListRepository(service)
    }

    @InternalCoroutinesApi
    @Provides
    @Singleton
    fun provideMainViewModel(repository: DataSource): MainViewModel
    {
        return MainViewModel(repository)
    }



}