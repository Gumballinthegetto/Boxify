package kh.edu.rupp.ite.boxify.view.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.boxify.databinding.ActivityAddFolderBinding
import kh.edu.rupp.ite.boxify.view.ui.fragment.MainItemFragment

class AddFolderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddFolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.itemPopupMenuAddFolderCloseBtn.setOnClickListener {
            val intent = Intent(this, MainItemFragment::class.java)
            startActivity(intent)
        }
    }
}