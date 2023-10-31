package kh.edu.rupp.ite.boxify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.FragmentStartupSecondpageBinding

class StartUpSecondPageFragment : Fragment() {

    private lateinit var binding : FragmentStartupSecondpageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartupSecondpageBinding.inflate(inflater, container, false)
        return binding.root
    }
}