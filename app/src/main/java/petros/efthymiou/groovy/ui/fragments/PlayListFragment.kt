package petros.efthymiou.groovy.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentPlayListBinding
import petros.efthymiou.groovy.ui.adapters.PlayListsAdapter


@AndroidEntryPoint
@InternalCoroutinesApi
class PlayListFragment : Fragment(),PlayListsAdapter.ItemClickListener {

    @InternalCoroutinesApi
    //val viewModel:MainViewModel by activityViewModels()
    private val viewModel:MainViewModel by viewModels()
    lateinit var binding:FragmentPlayListBinding
    lateinit var adapter:PlayListsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentPlayListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(null, "onCreateView: created", )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter = PlayListsAdapter()
        adapter.clickListener = this

        val recycler = binding.playListRecycler
        recycler.adapter = adapter
        getAdapterList()
        observeList()

    }

    private fun observeList() {

        viewModel.playList.observe(viewLifecycleOwner){
            Log.e(null, "observe: entered", )
            if (it != null)
            {
                Log.e(null, "observe: $it ", )

                adapter.submitList(it)

            }
        }


    }

//    private fun observeLoader()
//    {
//        viewModel.progressLiveData.observe(viewLifecycleOwner){
//            if (it)
//            binding.progressBar.visibility = View.VISIBLE
//            else
//                binding.progressBar.visibility = View.GONE
//        }
//    }

    private fun getAdapterList()
    {
        Log.e(null, "getAdapterList: entered ", )
        lifecycleScope.launch {
            viewModel.getPlayLists()
        }
    }

    override fun clicked(id: String) {

        findNavController().navigate(R.id.playListDetailsFragment)
    }


}