package petros.efthymiou.groovy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import petros.efthymiou.groovy.GroovyApp_HiltComponents
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.remote.PlayListService
import petros.efthymiou.groovy.repositories.PlayListRepository
import petros.efthymiou.groovy.ui.fragments.MainViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {


    @Provides
    @Singleton
    fun providesPlayListService():PlayListService
    {
        return PlayListService()
    }

    @Provides
    @Singleton
    fun provideRepository(service: PlayListService):DataSource
    {
        return PlayListRepository(service)
    }

    @InternalCoroutinesApi
    @Provides
    @Singleton
    fun provideMainViewModel(repository: DataSource):MainViewModel
    {
        return MainViewModel(repository)
    }
}