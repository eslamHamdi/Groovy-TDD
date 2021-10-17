package petros.efthymiou.groovy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.FragmentPlayListDetailsBinding


class PlayListDetailsFragment : Fragment() {

    lateinit var binding:FragmentPlayListDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayListDetailsBinding.inflate(inflater)
        return binding.root

    }


    }
