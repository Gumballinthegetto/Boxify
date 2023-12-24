package kh.edu.rupp.ite.boxify.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.FragmentMainDashboardBinding

class MainDashBoardFragment : Fragment() {

    private lateinit var binding: FragmentMainDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
}