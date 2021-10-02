package petros.efthymiou.groovy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import petros.efthymiou.groovy.GroovyApp_HiltComponents
import petros.efthymiou.groovy.domain.DataSource
import petros.efthymiou.groovy.repositories.PlayListRepository
import petros.efthymiou.groovy.ui.fragments.MainViewModel

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {


    @Provides
    fun provideRepository():DataSource
    {
        return PlayListRepository()
    }

    @InternalCoroutinesApi
    @Provides
    fun provideMainViewModel(repository: PlayListRepository):MainViewModel
    {
        return MainViewModel(repository as DataSource)
    }
}