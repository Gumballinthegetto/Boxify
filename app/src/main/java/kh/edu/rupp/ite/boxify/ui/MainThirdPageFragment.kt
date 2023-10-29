package kh.edu.rupp.ite.boxify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.FragmentStartupThirdpageBinding

class MainThirdPageFragment : Fragment() {

    private lateinit var binding : FragmentStartupThirdpageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartupThirdpageBinding.inflate(inflater, container, false)
        return binding.root
    }
}