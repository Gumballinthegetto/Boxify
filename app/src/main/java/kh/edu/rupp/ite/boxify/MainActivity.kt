package kh.edu.rupp.ite.boxify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.boxify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterBtn.setOnClickListener {
            intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }
}