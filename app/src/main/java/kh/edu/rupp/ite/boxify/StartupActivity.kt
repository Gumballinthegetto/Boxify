package kh.edu.rupp.ite.boxify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.boxify.adapter.StartUpViewPager2Adapter
import kh.edu.rupp.ite.boxify.databinding.ActivityStartupBinding
import kh.edu.rupp.ite.boxify.ui.MainFirstPageFragment
import kh.edu.rupp.ite.boxify.ui.MainSecondPageFragment
import kh.edu.rupp.ite.boxify.ui.MainThirdPageFragment

class StartupActivity : AppCompatActivity() {

    private lateinit var binding : ActivityStartupBinding
    private lateinit var adapter : StartUpViewPager2Adapter
    private val mainFirstPageFragment = MainFirstPageFragment()
    private val mainSecondPageFragment = MainSecondPageFragment()
    private val mainThirdPageFragment = MainThirdPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startUpViewPager2 = binding.layoutViewPager2Startup
        adapter = StartUpViewPager2Adapter(supportFragmentManager, lifecycle)

        adapter.addFragment(mainFirstPageFragment)
        adapter.addFragment(mainSecondPageFragment)
        adapter.addFragment(mainThirdPageFragment)

        startUpViewPager2.adapter = adapter

        binding.getStartedBtn.setOnClickListener {
            intent = Intent(it.context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}