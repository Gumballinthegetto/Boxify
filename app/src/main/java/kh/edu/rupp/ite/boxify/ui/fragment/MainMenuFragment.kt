package kh.edu.rupp.ite.boxify.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

}