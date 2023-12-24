package kh.edu.rupp.ite.boxify.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.boxify.databinding.FragmentMainItemBinding

class MainItemFragment : Fragment() {

    private lateinit var binding: FragmentMainItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomDialog()
    }

    private fun showBottomDialog() {
        binding.apply {
            binding.itemAddItemBtn.setOnClickListener {
                val itemPopUpMenu = MainItemPopupMenuFragment()
                parentFragmentManager.let { itemPopUpMenu.show(it, MainItemPopupMenuFragment.TAG) }
            }
        }
    }
}