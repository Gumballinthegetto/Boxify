package kh.edu.rupp.ite.boxify.view.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kh.edu.rupp.ite.boxify.databinding.FragmentMainItemPopupMenuBinding
import kh.edu.rupp.ite.boxify.view.ui.activity.AddFolderActivity
import kh.edu.rupp.ite.boxify.view.ui.activity.AddItemActivity

class MainItemPopupMenuFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMainItemPopupMenuBinding

    companion object {
        const val TAG = "ItemPopUpMenuFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainItemPopupMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.itemPopupMenuAddItemBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AddItemActivity::class.java)
                startActivity(intent)
                this.dismiss()
            }
        }

        binding.itemPopupMenuAddFolderBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AddFolderActivity::class.java)
                startActivity(intent)
                this.dismiss()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }
}