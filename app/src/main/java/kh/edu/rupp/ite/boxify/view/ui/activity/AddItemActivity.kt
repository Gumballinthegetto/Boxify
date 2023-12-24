package kh.edu.rupp.ite.boxify.view.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityAddItemBinding
import kh.edu.rupp.ite.boxify.view.ui.fragment.MainItemFragment

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemPopupMenuAddItemCloseBtn.setOnClickListener {
            val intent = Intent(this, MainItemFragment::class.java)
            startActivity(intent)
        }
    }
}