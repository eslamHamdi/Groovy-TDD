package petros.efthymiou.testsuite

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite
import petros.efthymiou.groovy.remote.PlayListService
import petros.efthymiou.groovy.remote.PlayListServiceShould
import petros.efthymiou.groovy.repositories.PlayListRepositoryShould
import petros.efthymiou.groovy.ui.MainViewModelShould

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(Suite::class)
 @Suite.SuiteClasses(MainViewModelShould::class,PlayListRepositoryShould::class,PlayListServiceShould::class)
class UnitTestSuite {
}