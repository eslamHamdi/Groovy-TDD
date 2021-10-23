package petros.efthymiou.groovy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentPlayListDetailsBinding
import javax.inject.Inject

@InternalCoroutinesApi
@AndroidEntryPoint
class PlayListDetailsFragment : Fragment() {

    lateinit var binding:FragmentPlayListDetailsBinding


    val viewModel:MainViewModel by activityViewModels()
private val args:PlayListDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayListDetailsBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.listId

        binding.lifecycleOwner = this

       viewLifecycleOwner.lifecycleScope.launch {
           observe(id)
       }

    }

   private suspend fun  observe(id:String){

       viewModel.getPlayListsDetails(id)
       viewModel.playListDetails.observe(viewLifecycleOwner){
           binding.detailItem = it

       }

   }
    }
