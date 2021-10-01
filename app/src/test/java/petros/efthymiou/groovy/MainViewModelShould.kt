package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule


@ExperimentalCoroutinesApi
class MainViewModelShould {


    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

}