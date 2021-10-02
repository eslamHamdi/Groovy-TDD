package petros.efthymiou.groovy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentPlayListBinding
import petros.efthymiou.groovy.ui.adapters.PlayListsAdapter


@AndroidEntryPoint
@InternalCoroutinesApi
class PlayListFragment : Fragment() {

    @InternalCoroutinesApi
    val viewModel:MainViewModel by activityViewModels()
    lateinit var binding:FragmentPlayListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_play_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        observe()
    }

    private fun observe() {
        val recycler = binding.playListRecycler
        val adapter = PlayListsAdapter()
        viewModel.playList.observe(viewLifecycleOwner){
            if (it != null)
            {
                adapter.submitList(it)
                recycler.adapter = adapter
            }

        }
    }


}