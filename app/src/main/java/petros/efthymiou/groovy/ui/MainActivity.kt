package petros.efthymiou.groovy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.ActivityMainBinding
import petros.efthymiou.groovy.ui.fragments.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


  // private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)


        setupActionBarWithNavController(navController,appBarConfiguration)

}




}