package kh.edu.rupp.ite.boxify.view.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.boxify.base.BaseActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityAddItemBinding
import kh.edu.rupp.ite.boxify.view.ui.fragment.MainItemFragment

class AddItemActivity : BaseActivity<ActivityAddItemBinding>(ActivityAddItemBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAction()
    }
    private fun initAction(){
        binding.itemPopupMenuAddItemCloseBtn.setOnClickListener {
            finish()
        }
    }
}