package kh.edu.rupp.ite.boxify.view.ui.fragment

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.boxify.adapter.ItemAdapter
import kh.edu.rupp.ite.boxify.data.model.ItemModel
import kh.edu.rupp.ite.boxify.databinding.FragmentMainItemBinding
import kh.edu.rupp.ite.boxify.helper.MessageUtils
import kh.edu.rupp.ite.boxify.helper.SharedPreferencesManager
import kh.edu.rupp.ite.boxify.internet.client.ApiClient
import kh.edu.rupp.ite.boxify.view_model.AddItemViewModel
import kh.edu.rupp.ite.boxify.view_model.ViewModelFactory

class MainItemFragment : Fragment() {

    private lateinit var binding: FragmentMainItemBinding
    private lateinit var mViewModel: AddItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainItemBinding.inflate(inflater, container, false)
        mViewModel = ViewModelProvider(this, ViewModelFactory(requireContext()))[AddItemViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        doObserve()
        showBottomDialog()
    }

    private fun initView(){
        binding.headerLayout.visibility = View.GONE
    }

    private fun doObserve(){
        mViewModel.isLoading.observe(requireActivity()){
            binding.loadingView.root.visibility = if (it) View.VISIBLE else View.GONE
        }
        mViewModel.errorMessage.observe(requireActivity()){
            MessageUtils.showError(requireActivity(), "Error", it)
        }
        mViewModel.getListFolderResponseLiveData.observe(requireActivity()){
            if (it.success){
                it.data?.let { itemModels: ArrayList<ItemModel> ->
                    updateUi(itemModels)
                }
            }else{
                MessageUtils.showError(requireActivity(), "Error", it.message)
            }
        }
    }

    private fun updateUi(itemModels: ArrayList<ItemModel>){
        if (itemModels.isEmpty()){
            binding.noDataFoundLayout.visibility = View.VISIBLE
            binding.headerLayout.visibility = View.GONE
            binding.itemRecyclerView.visibility = View.GONE
        }else{
            itemAdapter = ItemAdapter(itemModels)
            binding.itemRecyclerView.apply {
                adapter = itemAdapter
                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                visibility = View.VISIBLE
            }
            binding.headerLayout.visibility = View.VISIBLE
            binding.itemNumberTv.text = String.format("%d", itemModels.size)
        }
    }
    private fun showBottomDialog() {
        binding.itemAddItemBtn.setOnClickListener {
            val itemPopUpMenu = MainItemPopupMenuFragment()
            parentFragmentManager.let { itemPopUpMenu.show(it, MainItemPopupMenuFragment.TAG) }
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getListFolder()
    }
}